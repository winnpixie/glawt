package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.DrawMode;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.Vertex;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class EndDrawCommand implements Command {
    @Override
    public boolean execute(GraphicsContext context) {
        if (context.getDrawMode() == -1) return false;

        // I doubt this command got implemented even semi-correctly, but whatever.
        Vertex[] vertices = context.getVertices().toArray(new Vertex[0]);
        Vertex origin = vertices[0];

        switch (context.getDrawMode()) {
            case DrawMode.GL_POINTS: { // PTc = Vc
                for (int i = 0; i < vertices.length; i++) {
                    origin = vertices[i];

                    float pointSize = context.getPointSize();
                    float halfSize = pointSize / 2f;

                    context.getDriver().fill(new Rectangle2D.Double(
                            origin.getX() - halfSize,
                            origin.getY() - halfSize,
                            pointSize,
                            pointSize));
                }
                break;
            }

            case DrawMode.GL_LINES: { // Lc = Vc / 2
                for (int i = 0; i < vertices.length; i += 2) {
                    origin = vertices[i];
                    Vertex end = vertices[i + 1];

                    Path2D.Double linePath = new Path2D.Double();

                    linePath.moveTo(origin.getX(), origin.getY());
                    linePath.lineTo(end.getX(), end.getY());

                    context.getDriver().draw(linePath);
                }

                break;
            }

            case DrawMode.GL_LINE_STRIP: { // Lc = Vc
                Path2D.Double linePath = new Path2D.Double();

                linePath.moveTo(origin.getX(), origin.getY());

                for (int i = 1; i < vertices.length; i++) {
                    Vertex vert = vertices[i];
                    linePath.lineTo(vert.getX(), vert.getY());
                }

                context.getDriver().draw(linePath);
                break;
            }

            case DrawMode.GL_LINE_LOOP: { // Lc = Vc + 1
                Path2D.Double linePath = new Path2D.Double();

                linePath.moveTo(origin.getX(), origin.getY());

                for (int i = 1; i < vertices.length; i++) {
                    Vertex vert = vertices[i];
                    linePath.lineTo(vert.getX(), vert.getY());
                }

                linePath.lineTo(origin.getX(), origin.getY());

                context.getDriver().draw(linePath);
                break;
            }

            case DrawMode.GL_TRIANGLES: { // Tc = Vc / 3
                Vertex[][] triangles = createTriangles(vertices);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case DrawMode.GL_TRIANGLE_STRIP: { // Tc = Vc - 2
                Vertex[][] triangles = createChainedTriangles(vertices);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case DrawMode.GL_TRIANGLE_FAN: { // Tc = VXc - 2
                Vertex[][] triangles = createChainedTriangles(vertices);

                for (int i = 0; i < triangles.length; i++) {
                    triangles[i][0] = origin;
                    drawPolygon(triangles[i], context);
                }
                break;
            }

            case DrawMode.GL_QUADS: { // Qc = Vc / 4
                Vertex[][] quads = createQuads(vertices);

                for (int i = 0; i < quads.length; i++) drawPolygon(quads[i], context);
                break;
            }

            case DrawMode.GL_QUAD_STRIP: { // Qc = (Vc / 2) - 1
                Vertex[][] quads = createChainedQuads(vertices);

                for (int i = 0; i < quads.length; i++) drawPolygon(quads[i], context);
                break;
            }

            case DrawMode.GL_POLYGON: { // Pc = 1
                drawPolygon(vertices, context);
                break;
            }
        }

        context.getVertices().clear();
        context.setDrawMode((byte) -1);

        return true;
    }

    private void drawPolygon(Vertex[] vertices, GraphicsContext context) {
        Path2D.Double shape = new Path2D.Double();

        Vertex vertex = vertices[0];
        shape.moveTo(vertex.getX(), vertex.getY());

        for (int i = 1; i < vertices.length; i++) {
            vertex = vertices[i];

            shape.lineTo(vertex.getX(), vertex.getY());
        }

        context.getDriver().fill(shape);
    }

    private Vertex[][] createTriangles(Vertex[] vertices) {
        Vertex[][] triangles = new Vertex[vertices.length / 3][3];

        for (int t = 0; t < triangles.length; t++) {
            triangles[t][0] = vertices[(t * 3)];
            triangles[t][1] = vertices[(t * 3) + 1];
            triangles[t][2] = vertices[(t * 3) + 2];
        }

        return triangles;
    }

    private Vertex[][] createChainedTriangles(Vertex[] vertices) {
        Vertex[][] triangles = new Vertex[vertices.length - 2][3];

        for (int t = 0; t < triangles.length; t++) {
            triangles[t][0] = vertices[t];
            triangles[t][1] = vertices[t + 1];
            triangles[t][2] = vertices[t + 2];
        }

        return triangles;
    }

    private Vertex[][] createQuads(Vertex[] vertices) {
        Vertex[][] quads = new Vertex[vertices.length / 4][4];

        for (int q = 0; q < quads.length; q++) {
            quads[q][0] = vertices[(q * 4)];
            quads[q][1] = vertices[(q * 4) + 1];
            quads[q][2] = vertices[(q * 4) + 2];
            quads[q][3] = vertices[(q * 4) + 3];
        }

        return quads;
    }

    private Vertex[][] createChainedQuads(Vertex[] vertices) {
        Vertex[][] quads = new Vertex[(vertices.length / 2) - 1][4];

        for (int q = 0; q < quads.length; q++) {
            quads[q][0] = vertices[(q * 2)];
            quads[q][1] = vertices[(q * 2) + 1];
            quads[q][2] = vertices[(q * 2) + 2];
            quads[q][3] = vertices[(q * 2) + 3];
        }

        return quads;
    }

    private double[] project(Vertex vertex) {
        double z = vertex.getZ() + 1.0;

        return new double[]{
                vertex.getX() / z,
                vertex.getY() / z
        };
    }

    // FIXME: Add proper mesh generator, right now this behaves like GL_TRIANGLE_FAN
    private Vertex[][] createTriangleMesh(Vertex[] vertices) {
        Vertex[][] triangles = new Vertex[vertices.length - 2][3];

        for (int t = 0; t < triangles.length; t++) {
            triangles[t][0] = vertices[0];
            triangles[t][1] = vertices[t + 1];
            triangles[t][2] = vertices[t + 2];
        }

        return triangles;
    }
}
