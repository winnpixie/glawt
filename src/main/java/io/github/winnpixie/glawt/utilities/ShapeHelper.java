package io.github.winnpixie.glawt.utilities;

import io.github.winnpixie.glawt.vertex.Color;
import io.github.winnpixie.glawt.vertex.Position;
import io.github.winnpixie.glawt.vertex.Vertex;

public class ShapeHelper {
    private ShapeHelper() {
    }

    public static Vertex[][] createLines(Vertex[] vertices) {
        Vertex[][] lines = new Vertex[vertices.length / 2][2];

        for (int l = 0; l < lines.length; l++) {
            lines[l][0] = vertices[(l * 2)];
            lines[l][1] = vertices[(l * 2) + 1];
        }

        return lines;
    }

    public static Vertex[] createLineLoop(Vertex[] vertices) {
        Vertex[] lines = new Vertex[vertices.length + 1];

        System.arraycopy(vertices, 0, lines, 0, vertices.length);
        lines[vertices.length] = vertices[0];

        return lines;
    }

    public static Vertex[][] createTriangles(Vertex[] vertices) {
        Vertex[][] triangles = new Vertex[vertices.length / 3][3];

        for (int t = 0; t < triangles.length; t++) {
            triangles[t][0] = vertices[(t * 3)];
            triangles[t][1] = vertices[(t * 3) + 1];
            triangles[t][2] = vertices[(t * 3) + 2];
        }

        return triangles;
    }

    public static Vertex[][] createTriangleChain(Vertex[] vertices) {
        Vertex[][] triangles = new Vertex[vertices.length - 2][3];

        for (int t = 0; t < triangles.length; t++) {
            triangles[t][0] = vertices[t];
            triangles[t][1] = vertices[t + 1];
            triangles[t][2] = vertices[t + 2];
        }

        return triangles;
    }

    public static Vertex[][] createQuads(Vertex[] vertices) {
        Vertex[][] quads = new Vertex[vertices.length / 4][4];

        for (int q = 0; q < quads.length; q++) {
            quads[q][0] = vertices[(q * 4)];
            quads[q][1] = vertices[(q * 4) + 1];
            quads[q][2] = vertices[(q * 4) + 2];
            quads[q][3] = vertices[(q * 4) + 3];
        }

        return quads;
    }

    public static Vertex[][] createQuadChain(Vertex[] vertices) {
        Vertex[][] quads = new Vertex[(vertices.length / 2) - 1][4];

        for (int q = 0; q < quads.length; q++) {
            quads[q][0] = vertices[(q * 2)];
            quads[q][1] = vertices[(q * 2) + 1];
            quads[q][2] = vertices[(q * 2) + 2];
            quads[q][3] = vertices[(q * 2) + 3];
        }

        return quads;
    }

    // Tc: Triangle Count
    // Vc: Vertex Count
    // Cv: Center Vertex
    // ---
    // Cv = avg([ v(0), v(1), v(2), ... ])
    // T(n) = [ Cv, v(n), v(n + 1) ]
    // ---
    // Tc = Vc
    // ---
    // FIXME: This is a worst-case algorithm I thought of at work, lol.
    public static Vertex[][] tessellate(Vertex[] vertices) {
        if (vertices.length == 3) return new Vertex[][]{vertices}; // No point in triangulating a triangle.

        int vertexCount = vertices.length;

        double cx = 0.0;
        double cy = 0.0;
        double cz = 0.0;
        double cw = 1.0; // "cw" will [almost?] always be 1, there is no sense in computing its average.

        float r = 0f;
        float g = 0f;
        float b = 0f;
        float a = 0f;

        for (int i = 0; i < vertexCount; i++) {
            Vertex vertex = vertices[i];

            cx += vertex.position().x();
            cy += vertex.position().y();
            cz += vertex.position().z();

            r += vertex.color().red();
            g += vertex.color().green();
            b += vertex.color().blue();
            a += vertex.color().alpha();
        }

        Vertex center = new Vertex(new Position(cx / vertexCount, cy / vertexCount, cz / vertexCount, cw),
                new Color(r / vertexCount, g / vertexCount, b / vertexCount, a / vertexCount));

        Vertex[][] triangles = new Vertex[vertexCount][3];
        for (int t = 0; t < vertexCount; t++) {
            triangles[t][0] = center;
            triangles[t][1] = vertices[t];
            triangles[t][2] = vertices[(t + 1) % vertexCount];
        }

        return triangles;
    }
}
