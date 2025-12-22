package io.github.winnpixie.glawt.immediate;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.utilities.ShapeHelper;
import io.github.winnpixie.glawt.vertex.Color;
import io.github.winnpixie.glawt.vertex.Vertex;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VertexManager {
    private final List<Vertex> vertices = Collections.synchronizedList(new ArrayList<>());

    private VertexMode mode = VertexMode.NONE;
    private Color color;
    private float lineWidth = 1f;
    private float pointSize = 1f;

    public List<Vertex> getVertices() {
        return vertices;
    }

    public VertexMode getMode() {
        return mode;
    }

    public void setMode(VertexMode mode) {
        if (mode == null) mode = VertexMode.NONE;

        this.mode = mode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public float getPointSize() {
        return pointSize;
    }

    public void setPointSize(float pointSize) {
        this.pointSize = pointSize;
    }

    public void draw(GLContext context) {
        // I doubt this implementation is even semi-correct, but I don't care right now.
        Vertex[] vertexArray = vertices.toArray(new Vertex[0]);

        switch (mode) {
            case NONE:
                break;
            case VertexMode.GL_POINTS: { // Pc = Vc
                for (int i = 0; i < vertexArray.length; i++) drawPoint(vertexArray[i], context);
                break;
            }

            case VertexMode.GL_LINES: { // Lc = Vc / 2
                Vertex[][] lines = ShapeHelper.createLines(vertexArray);

                for (int i = 0; i < lines.length; i++) drawLine(lines[i], context);
                break;
            }

            case VertexMode.GL_LINE_STRIP: { // Lc = Vc
                drawLine(vertexArray, context);
                break;
            }

            case VertexMode.GL_LINE_LOOP: { // Lc = Vc + 1
                drawLine(ShapeHelper.createLineLoop(vertexArray), context);
                break;
            }

            case VertexMode.GL_TRIANGLES: { // Tc = Vc / 3
                Vertex[][] triangles = ShapeHelper.createTriangles(vertexArray);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case VertexMode.GL_TRIANGLE_STRIP: { // Tc = Vc - 2
                Vertex[][] triangles = ShapeHelper.createTriangleChain(vertexArray);

                for (int i = 0; i < triangles.length; i++) drawPolygon(triangles[i], context);
                break;
            }

            case VertexMode.GL_TRIANGLE_FAN: { // Tc = Vc - 2
                Vertex origin = vertexArray[0];
                Vertex[][] triangles = ShapeHelper.createTriangleChain(vertexArray);

                for (int i = 0; i < triangles.length; i++) {
                    triangles[i][0] = origin;
                    drawPolygon(triangles[i], context);
                }
                break;
            }

            case VertexMode.GL_QUADS: { // Qc = Vc / 4
                Vertex[][] quads = ShapeHelper.createQuads(vertexArray);

                for (int i = 0; i < quads.length; i++) drawPolygon(quads[i], context);
                break;
            }

            case VertexMode.GL_QUAD_STRIP: { // Qc = (Vc / 2) - 1
                Vertex[][] quads = ShapeHelper.createQuadChain(vertexArray);

                for (int i = 0; i < quads.length; i++) drawPolygon(quads[i], context);
                break;
            }

            case VertexMode.GL_POLYGON: { // Pc = 1
                drawPolygon(vertexArray, context);
                break;
            }
        }
    }

    private void drawPoint(Vertex vertex, GLContext context) {
        Rectangle2D.Double pointRect = new Rectangle2D.Double(
                vertex.position().x() - (pointSize / 2.0),
                vertex.position().y() - (pointSize / 2.0),
                pointSize, pointSize
        );

        context.getActiveGraphics().setColor(vertex.color().awtColor());
        context.getActiveGraphics().fill(pointRect);
    }

    private void drawLine(Vertex[] vertices, GLContext context) {
        Path2D.Double linePath = new Path2D.Double();

        Vertex vertex = vertices[0];
        linePath.moveTo(vertex.position().x(), vertex.position().y());

        for (int i = 0; i < vertices.length; i++) {
            vertex = vertices[i];
            linePath.lineTo(vertex.position().x(), vertex.position().y());
        }

        context.getActiveGraphics().setColor(vertices[0].color().awtColor());
        context.getActiveGraphics().draw(linePath);
    }

    // TODO: Ween off of AWT dependence to allow finer draw control.
    private void drawPolygon(Vertex[] vertices, GLContext context) {
        Vertex[][] tessellated = ShapeHelper.tessellate(vertices);

        for (Vertex[] triangle : tessellated) {
            Path2D.Double trianglePath = new Path2D.Double();

            Vertex vertex = triangle[0];
            trianglePath.moveTo(vertex.position().x(), vertex.position().y());

            for (int i = 1; i < triangle.length; i++) {
                vertex = triangle[i];
                trianglePath.lineTo(vertex.position().x(), vertex.position().y());
            }

            context.getActiveGraphics().setColor(triangle[0].color().awtColor());
            context.getActiveGraphics().fill(trianglePath);
        }
    }
}
