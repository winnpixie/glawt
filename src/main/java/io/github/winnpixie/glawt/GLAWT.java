package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.commands.*;

public class GLAWT {
    private static GraphicsContext context;

    public static void setContext(GraphicsContext ctx) {
        context = ctx;
    }

    public static GraphicsContext getContext() {
        return context;
    }

    private static void queueCommand(Command command) {
        DisplayLists.getActiveList().add(command);
    }

    public static int glGenLists(int range) {
        if (range == 0) return 0;

        int name = DisplayLists.createList();

        for (int i = 1; i < range; i++) DisplayLists.createList();

        return name;
    }

    public static void glNewList(int name, byte mode) {
        if (name == 0) return;

        CommandList list = new CommandList(mode);
        DisplayLists.setList(name, list);
        DisplayLists.setActiveList(name);
    }

    public static void glEndList() {
        DisplayLists.setActiveList(0);
    }

    public static void glCallList(int name) {
        queueCommand(new CallListCommand(name));
    }

    public static void glCallLists(int name, int range) {
        for (int i = 0; i < range; i++) glCallList(name + i);
    }

    public static void glDeleteLists(int name, int range) {
        for (int i = 0; i < range; i++) DisplayLists.deleteList(name + i);
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
        glColor4d(red, green, blue, alpha);
    }

    public static void glColor4d(double red, double green, double blue, double alpha) {
        glColor4i((int) (red * 255.0), (int) (green * 255.0), (int) (blue * 255.0), (int) (alpha * 255.0));
    }

    public static void glTranslatef(float x, float y, float z) {
        glTranslated(x, y, z);
    }

    public static void glTranslated(double x, double y, double z) {
        queueCommand(new TranslateCommand(x, y, z));
    }

    public static void glScalef(float x, float y, float z) {
        glScaled(x, y, z);
    }

    public static void glScaled(double x, double y, double z) {
        queueCommand(new ScaleCommand(x, y, z));
    }

    public static void glRotatef(float angle, float x, float y, float z) {
        glRotated(angle, x, y, z);
    }

    public static void glRotated(double angle, double x, double y, double z) {
        queueCommand(new RotateCommand(angle, x, y, z));
    }

    public static void glLineWidth(float size) {
        queueCommand(new LineWidthCommand(size));
    }

    public static void glPointSize(float size) {
        queueCommand(new PointSizeCommand(size));
    }

    public static void glVertex2i(int x, int y) {
        glVertex2d(x, y);
    }

    public static void glVertex2f(float x, float y) {
        glVertex2d(x, y);
    }

    public static void glVertex2d(double x, double y) {
        glVertex3d(x, y, 0);
    }

    public static void glVertex3i(int x, int y, int z) {
        glVertex3d(x, y, z);
    }

    public static void glVertex3f(float x, float y, float z) {
        glVertex3d(x, y, z);
    }

    public static void glVertex3d(double x, double y, double z) {
        glVertex4d(x, y, z, 1);
    }

    public static void glVertex4i(int x, int y, int z, int w) {
        glVertex4d(x, y, z, w);
    }

    public static void glVertex4f(float x, float y, float z, float w) {
        glVertex4d(x, y, z, w);
    }

    public static void glVertex4d(double x, double y, double z, double w) {
        queueCommand(new AddVertexCommand(x, y, z, w));
    }

    public static void glBegin(byte mode) {
        queueCommand(new BeginDrawCommand(mode));
    }

    public static void glEnd() {
        queueCommand(new EndDrawCommand());
    }
}
