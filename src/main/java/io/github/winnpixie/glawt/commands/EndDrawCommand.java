package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.VertexData;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class EndDrawCommand implements Command {
    @Override
    public boolean execute(GraphicsContext context) {
        if (context.getDrawMode() == null) return false;

        // This is probably NOT the correct thing to do, but whatever.
        List<VertexData> vertices = context.getVertices();
        int vertexCount = vertices.size();

        switch (context.getDrawMode()) {
            case GL_POINTS: {
                for (int i = 0; i < vertexCount; i++) {
                    VertexData vert = vertices.get(i);

                    context.getDriver().fill(new Rectangle2D.Double(
                            vert.getX(),
                            vert.getY(),
                            1.0,
                            1.0));
                }
                break;
            }

            case GL_LINES: {
                Path2D.Double linePath = new Path2D.Double();

                for (int i = 0; i < vertexCount; i += 2) {
                    VertexData start = vertices.get(i);
                    VertexData end = vertices.get(i + 1);

                    linePath.moveTo(start.getX(), start.getY());
                    linePath.lineTo(end.getX(), end.getY());
                }

                context.getDriver().draw(linePath);
                break;
            }

            case GL_LINE_STRIP: {
                VertexData origin = vertices.get(0);

                Path2D.Double linePath = new Path2D.Double();
                linePath.moveTo(origin.getX(), origin.getY());

                for (int i = 1; i < vertexCount; i++) {
                    VertexData vert = vertices.get(i);

                    linePath.lineTo(vert.getX(), vert.getY());
                }

                context.getDriver().draw(linePath);
                break;
            }

            case GL_LINE_LOOP: {
                VertexData origin = vertices.get(0);

                Path2D.Double linePath = new Path2D.Double();
                linePath.moveTo(origin.getX(), origin.getY());

                for (int i = 1; i < vertexCount; i++) {
                    VertexData vert = vertices.get(i);

                    linePath.lineTo(vert.getX(), vert.getY());
                }

                linePath.lineTo(origin.getX(), origin.getY());

                context.getDriver().draw(linePath);
                break;
            }

            case GL_TRIANGLES: {
                Path2D.Double trianglePath = new Path2D.Double();

                for (int i = 0; i < vertexCount; i += 3) {
                    VertexData cornerOne = vertices.get(i);
                    VertexData cornerTwo = vertices.get(i + 1);
                    VertexData cornerThree = vertices.get(i + 2);

                    trianglePath.moveTo(cornerOne.getX(), cornerOne.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());

                }

                context.getDriver().fill(trianglePath);
                break;
            }

            case GL_TRIANGLE_STRIP: {
                VertexData cornerOne = vertices.get(0);

                Path2D.Double trianglePath = new Path2D.Double();

                for (int i = 1; i < vertexCount; i += 2) {
                    VertexData cornerTwo = vertices.get(i);
                    VertexData cornerThree = vertices.get(i + 1);

                    trianglePath.moveTo(cornerOne.getX(), cornerOne.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());

                    cornerOne = cornerThree;
                }

                context.getDriver().fill(trianglePath);
                break;
            }

            case GL_TRIANGLE_FAN: {
                VertexData origin = vertices.get(0);

                Path2D.Double trianglePath = new Path2D.Double();

                for (int i = 1; i < vertexCount; i += 2) {
                    VertexData cornerTwo = vertices.get(i);
                    VertexData cornerThree = vertices.get(i + 1);

                    trianglePath.moveTo(origin.getX(), origin.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());
                }

                context.getDriver().fill(trianglePath);
                break;
            }

            case GL_QUADS: {
                Path2D.Double quadPath = new Path2D.Double();

                for (int i = 0; i < vertexCount; i += 4) {
                    VertexData cornerOne = vertices.get(i);
                    VertexData cornerTwo = vertices.get(i + 1);
                    VertexData cornerThree = vertices.get(i + 2);
                    VertexData cornerFour = vertices.get(i + 3);

                    quadPath.moveTo(cornerOne.getX(), cornerOne.getY());
                    quadPath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    quadPath.lineTo(cornerThree.getX(), cornerThree.getY());
                    quadPath.lineTo(cornerFour.getX(), cornerFour.getY());
                }

                context.getDriver().fill(quadPath);
                break;
            }

            case GL_QUAD_STRIP: {
                VertexData cornerOne = vertices.get(0);
                VertexData cornerTwo = vertices.get(1);

                Path2D.Double quadPath = new Path2D.Double();

                for (int i = 2; i < vertexCount; i += 2) {
                    VertexData cornerThree = vertices.get(i);
                    VertexData cornerFour = vertices.get(i + 1);

                    quadPath.moveTo(cornerOne.getX(), cornerOne.getY());
                    quadPath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    quadPath.lineTo(cornerThree.getX(), cornerThree.getY());
                    quadPath.lineTo(cornerFour.getX(), cornerFour.getY());

                    cornerOne = cornerThree;
                    cornerTwo = cornerFour;
                }

                context.getDriver().fill(quadPath);
                break;
            }

            case GL_POLYGON: {
                Path2D.Double polygonPath = new Path2D.Double();
                VertexData origin = vertices.get(0);
                polygonPath.moveTo(origin.getX(), origin.getY());

                for (int i = 1; i < vertexCount; i++) {
                    VertexData vert = vertices.get(i);

                    polygonPath.lineTo(vert.getX(), vert.getY());
                }

                context.getDriver().fill(polygonPath);
                break;
            }
        }

        context.getVertices().clear();
        context.setDrawMode(null);

        return true;
    }
}
