package io.github.winnpixie.glawt.utilities;

import io.github.winnpixie.glawt.GLAWT;
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

    public static Vertex[][] tessellate(Vertex[] vertices) {
        if (vertices.length == 3) { // Do not tessellate one triangle.
            return new Vertex[][]{vertices};
        }

        return switch (GLAWT.glawtGetShapeMode()) {
            case GLAWT_SHAPES_CONVEX -> tessellateSimple(vertices);
            case GLAWT_SHAPES_CONCAVE,
                 GLAWT_SHAPES_ANY -> tessellateAveraging(vertices);
        };
    }

    public static Vertex[][] tessellateAveraging(Vertex[] vertices) {
        /*
            Tc - Triangle count
            Vc - Vertex count
            Cv - Center vertex
            --------
            Cv = average([ V(0), V(1), V(2), V(...) ])
            T(n) = [ Cv, V(n), V(n + 1) ]
            --------
            Tc = Vc
         */

        int triangleCount = vertices.length;
        Vertex[][] triangles = new Vertex[triangleCount][3];

        Vertex centerVertex = computeCenter(vertices);

        for (int t = 0; t < triangleCount; t++) {
            triangles[t][0] = centerVertex;
            triangles[t][1] = vertices[t];
            triangles[t][2] = vertices[(t + 1) % triangleCount];
        }

        return triangles;
    }

    private static Vertex computeCenter(Vertex[] vertices) {
        int vertexCount = vertices.length;

        double sx = 0.0;
        double sy = 0.0;
        double sz = 0.0;
        double sw = 1.0;

        float sr = 0f;
        float sg = 0f;
        float sb = 0f;
        float sa = 0f;

        for (int i = 0; i < vertexCount; i++) {
            Vertex vertex = vertices[i];

            sx += vertex.position().x();
            sy += vertex.position().y();
            sz += vertex.position().z();
            // There is no need to compute the average for 'w', as it is [almost?] always 1.

            sr += vertex.color().red();
            sg += vertex.color().green();
            sb += vertex.color().blue();
            sa += vertex.color().alpha();
        }

        return new Vertex(new Position(sx / vertexCount, sy / vertexCount, sz / vertexCount, sw),
                new Color(sr / vertexCount, sg / vertexCount, sb / vertexCount, sa / vertexCount));
    }

    public static Vertex[][] tessellateSimple(Vertex[] vertices) {
        /*
            Tc - Triangle count
            Vc - Vertex count
            Ov - Origin vertex
            --------
            Ov = V(0)
            T(n) = [ Ov, V(n + 1), V(n + 2) ]
            --------
            Tc = Vc - 2
         */

        int triangleCount = vertices.length - 2;
        Vertex[][] triangles = new Vertex[triangleCount][3];

        Vertex originVertex = vertices[0];

        for (int t = 0; t < triangleCount; t++) {
            triangles[t][0] = originVertex;
            triangles[t][1] = vertices[t + 1];
            triangles[t][2] = vertices[t + 2];
        }

        return triangles;
    }
}
