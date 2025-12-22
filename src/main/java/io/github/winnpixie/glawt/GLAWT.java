package io.github.winnpixie.glawt;

import java.awt.*;

public class GLAWT {
    private GLAWT() {
    }

    public static GLContext glawtCreateContext(Graphics2D graphics) {
        GLContext context = new GLContext();
        context.getGraphics().push(graphics);

        return context;
    }

    public static void glawtSetContext(GLContext context) {
        OpenGL.setGLContext(context);
    }
}
