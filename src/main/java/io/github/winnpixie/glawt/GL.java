package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.commands.*;

public class GL {
    private static GraphicsContext context;

    public static void setContext(GraphicsContext ctx) {
        context = ctx;
    }

    public static GraphicsContext getContext() {
        return context;
    }

    private static void queueCommand(Command command) {
        GlobalContext.getActiveList().add(command);
    }

    public static int glGenLists(int range) {
        if (range == 0) return 0;

        int name = GlobalContext.createList();

        for (int i = 1; i < range; i++) GlobalContext.createList();

        return name;
    }

    public static void glNewList(int name, int mode) {
        glNewList(name, ListMode.fromGLInt(mode));
    }

    public static void glNewList(int name, ListMode mode) {
        if (name == 0) return;

        CommandList list = new CommandList(mode);
        GlobalContext.setList(name, list);
        GlobalContext.pushListStack(list);
    }

    public static void glEndList() {
        GlobalContext.popListStack();
    }

    public static void glCallList(int name) {
        queueCommand(new CallListCommand(name));
    }

    public static void glCallLists(int name, int range) {
        for (int i = 0; i < range; i++) glCallList(name + i);
    }

    public static void glDeleteLists(int name, int range) {
        for (int i = 0; i < range; i++) GlobalContext.deleteList(name + i);
    }

    public static void glPushMatrix() {
        queueCommand(new PushMatrixCommand());
    }

    public static void glPopMatrix() {
        queueCommand(new PopMatrixCommand());
    }

    public static void glColor3i(int red, int green, int blue) {
        glColor4i(red, green, blue, 255);
    }

    public static void glColor3f(float red, float green, float blue) {
        glColor4d(red, green, blue, 1.0);
    }

    public static void glColor3d(double red, double green, double blue) {
        glColor4d(red, green, blue, 1.0);
    }

    public static void glColor4i(int red, int green, int blue, int alpha) {
        queueCommand(new SetColorCommand(red, green, blue, alpha));
    }

    public static void glColor4f(float red, float green, float blue, float alpha) {
        glColor4d(red, green, blue, 1.0);
    }

    public static void glColor4d(double red, double green, double blue, double alpha) {
        glColor4i((int) (red * 255.0), (int) (green * 255.0), (int) (blue * 255.0), (int) (alpha * 255.0));
    }

    public static void glTranslatef(float x, float y) {
        glTranslated(x, y);
    }

    public static void glTranslated(double x, double y) {
        queueCommand(new TranslateCommand(x, y));
    }

    public static void glScalef(float x, float y) {
        glScaled(x, y);
    }

    public static void glScaled(double x, double y) {
        queueCommand(new ScaleCommand(x, y));
    }

    public static void glRotatef(float angle, float x, float y) {
        glRotated(angle, x, y);
    }

    public static void glRotated(double angle, double x, double y) {
        queueCommand(new RotateCommand(angle, x, y));
    }

    public static void glLineWidth(float size) {
        queueCommand(new LineWidthCommand(size));
    }

    public static void glVertex2i(int x, int y) {
        glVertex2d(x, y);
    }

    public static void glVertex2f(float x, float y) {
        glVertex2d(x, y);
    }

    public static void glVertex2d(double x, double y) {
        queueCommand(new AddVertexCommand(x, y));
    }

    public static void glBegin(int mode) {
        glBegin(DrawMode.fromGLInt(mode));
    }

    public static void glBegin(DrawMode mode) {
        queueCommand(new BeginDrawCommand(mode));
    }

    public static void glEnd() {
        queueCommand(new EndDrawCommand());
    }
}
