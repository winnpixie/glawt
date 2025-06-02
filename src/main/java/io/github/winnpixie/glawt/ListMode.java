package io.github.winnpixie.glawt;

public enum ListMode {
    GL_COMPILE(0),
    GL_COMPILE_AND_EXECUTE(1);

    private final int mode;

    ListMode(int mode) {
        this.mode = mode;
    }

    public static ListMode fromGLInt(int mode) {
        for (ListMode listMode : values()) {
            if (listMode.mode == mode) return listMode;
        }

        return null;
    }
}
