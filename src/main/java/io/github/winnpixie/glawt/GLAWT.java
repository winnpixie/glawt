package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.vertex.Vertex;

import java.awt.*;

public class GLAWT {
    private static ShapeMode shapeMode = ShapeMode.GLAWT_SHAPES_ANY;

    private GLAWT() {
    }

    public static ShapeMode glawtGetShapeMode() {
        return shapeMode;
    }

    /**
     * Sets the shape mode glAWT will use to determine the best tessellation technique for drawing.
     *
     * @param mode The mode to use for drawing
     */
    public static void glawtSetShapeMode(ShapeMode mode) {
        shapeMode = mode;
    }

    public static GLContext glawtCreateContext(Graphics2D graphics) {
        GLContext context = new GLContext();
        context.getGraphics().push(graphics);

        return context;
    }

    public static void glawtSetContext(GLContext context) {
        OpenGL.setGLContext(context);
    }

    public enum ShapeMode {
        /**
         * Indicates to glAWT that all shaped vertex arrays provided for draw calls represent convex shapes.
         * Using this forces the use of the "Simple" tessellation algorithm.
         *
         * @see io.github.winnpixie.glawt.utilities.ShapeHelper#tessellateSimple(Vertex[])
         */
        GLAWT_SHAPES_CONVEX,
        /**
         * Indicates glAWT that all shaped vertex arrays provided for draw calls represent concave shapes.
         * Using this forces the use of the "Averaging" tessellation algorithm.
         *
         * @see io.github.winnpixie.glawt.utilities.ShapeHelper#tessellateAveraging(Vertex[])
         */
        GLAWT_SHAPES_CONCAVE,
        /**
         * Tells glAWT that all shaped vertex arrays provided for draw calls may represent either convex or concave shapes.
         * Using this is (currently) equivalent to {@link ShapeMode#GLAWT_SHAPES_CONCAVE}
         */
        GLAWT_SHAPES_ANY
    }
}
