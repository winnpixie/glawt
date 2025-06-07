package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.DrawMode;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.utilities.ShapeHelper;
import io.github.winnpixie.glawt.vertex.Vertex;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class EndDrawCommand implements Command {
    @Override
    public boolean execute(GraphicsContext context) {
        if (context.getDrawMode() == -1) return false;

        java.awt.Color awtColor = context.getDriver().getColor();

        // I doubt this command got implemented even semi-correctly, but whatever.
        Vertex[] vertices = context.getVertices().toArray(new Vertex[0]);

        switch (context.getDrawMode()) {
            case DrawMode.GL_POINTS: { // Pc = Vc
                for (int i = 0; i < vertices.length; i++) drawPoint(vertices[i], context);
                break;
            }

            case DrawMode.GL_LINES: { // Lc = Vc / 2
                Vertex[][] lines = ShapeHelper.createLines(vertices);

                for (int i = 0; i < lines.length; i++) drawLine(lines[i], context);
                break;
            }

            case DrawMode.GL_LINE_STRIP: { // Lc = Vc
                drawLine(vertices, context);
                break;
            }

            case DrawMode.GL_LINE_LOOP: { // Lc = Vc + 1
                drawLine(ShapeHelper.createLineLoop(vertices), context);
                break;
            }

            case DrawMode.GL_TRIANGLES: { // Tc = Vc / 3
                Vertex[][] triangles = ShapeHelper.createTriangles(vertices);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case DrawMode.GL_TRIANGLE_STRIP: { // Tc = Vc - 2
                Vertex[][] triangles = ShapeHelper.createTriangleChain(vertices);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case DrawMode.GL_TRIANGLE_FAN: { // Tc = VXc - 2
                Vertex origin = vertices[0];
                Vertex[][] triangles = ShapeHelper.createTriangleChain(vertices);

                for (int i = 0; i < triangles.length; i++) {
                    triangles[i][0] = origin;
                    drawPolygon(triangles[i], context);
                }
                break;
            }

            case DrawMode.GL_QUADS: { // Qc = Vc / 4
                Vertex[][] quads = ShapeHelper.createQuads(vertices);

                for (int i = 0; i < quads.length; i++) drawPolygon(quads[i], context);
                break;
            }

            case DrawMode.GL_QUAD_STRIP: { // Qc = (Vc / 2) - 1
                Vertex[][] quads = ShapeHelper.createQuadChain(vertices);

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

        // Restore color
        context.getDriver().setColor(awtColor);

        return true;
    }

    private void drawPoint(Vertex vertex, GraphicsContext context) {
        double size = context.getPointSize();
        Rectangle2D.Double pointRect = new Rectangle2D.Double(
                vertex.getPosition().getX() - (size / 2.0),
                vertex.getPosition().getY() - (size / 2.0),
                size, size
        );

        context.getDriver().setColor(vertex.getColor().getAwtColor());
        context.getDriver().fill(pointRect);
    }

    private void drawLine(Vertex[] vertices, GraphicsContext context) {
        Path2D.Double linePath = new Path2D.Double();

        Vertex vertex = vertices[0];
        linePath.moveTo(vertex.getPosition().getX(), vertex.getPosition().getY());

        for (int i = 0; i < vertices.length; i++) {
            vertex = vertices[i];
            linePath.lineTo(vertex.getPosition().getX(), vertex.getPosition().getY());
        }

        context.getDriver().setColor(vertices[0].getColor().getAwtColor());
        context.getDriver().draw(linePath);
    }

    private void drawPolygon(Vertex[] vertices, GraphicsContext context) {
        Vertex[][] tessellated = ShapeHelper.triangulate(vertices); // TODO: Ween off of AWT dependence to allow finer draw control.

        for (Vertex[] triangle : tessellated) {
            Path2D.Double trianglePath = new Path2D.Double();

            Vertex vertex = triangle[0];
            trianglePath.moveTo(vertex.getPosition().getX(), vertex.getPosition().getY());

            for (int i = 1; i < triangle.length; i++) {
                vertex = triangle[i];
                trianglePath.lineTo(vertex.getPosition().getX(), vertex.getPosition().getY());
            }

            context.getDriver().setColor(triangle[0].getColor().getAwtColor());
            context.getDriver().fill(trianglePath);
        }
    }
}
