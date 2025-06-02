package io.github.winnpixie.glawt;

public enum DrawMode {
    GL_POINTS(0),

    GL_LINES(1),
    GL_LINE_STRIP(2),
    GL_LINE_LOOP(3),

    GL_TRIANGLES(4),
    GL_TRIANGLE_STRIP(5),
    GL_TRIANGLE_FAN(6),

    GL_QUADS(7),
    GL_QUAD_STRIP(8),

    GL_POLYGON(9);

    private final int mode;

    DrawMode(int mode) {
        this.mode = mode;
    }

    public static DrawMode fromGLInt(int mode) {
        for (DrawMode drawMode : values()) {
            if (drawMode.mode == mode) return drawMode;
        }

        return null;
    }
}
