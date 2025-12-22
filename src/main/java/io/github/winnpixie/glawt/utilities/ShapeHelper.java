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
    // FIXME: This is, like, a worst-case algorithm I thought of at work, lol.
    public static Vertex[][] tessellate(Vertex[] vertices) {
        if (vertices.length == 3) return new Vertex[][]{vertices}; // No point in triangulating a triangle.

        int vertexCount = vertices.length;
        Vertex[][] triangles = new Vertex[vertexCount][3];

        double vx = 0.0;
        double vy = 0.0;
        double vz = 0.0;
        double vw = 1.0; // Will [almost?] always be 1, there is no sense in computing its average.

        float vr = 0f;
        float vg = 0f;
        float vb = 0f;
        float va = 0f;

        for (int i = 0; i < vertexCount; i++) {
            Vertex vertex = vertices[i];

            vx += vertex.position().x();
            vy += vertex.position().y();
            vz += vertex.position().z();

            vr += vertex.color().red();
            vg += vertex.color().green();
            vb += vertex.color().blue();
            va += vertex.color().alpha();
        }

        Vertex centerVertex = new Vertex(new Position(vx / vertexCount, vy / vertexCount, vz / vertexCount, vw),
                new Color(vr / vertexCount, vg / vertexCount, vb / vertexCount, va / vertexCount));

        for (int t = 0; t < vertexCount; t++) {
            triangles[t][0] = centerVertex;
            triangles[t][1] = vertices[t];
            triangles[t][2] = vertices[(t + 1) % vertexCount];
        }

        return triangles;
    }
}
