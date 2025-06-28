package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.commands.DisplayList;
import io.github.winnpixie.glawt.commands.DisplayListMode;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.commands.impl.CallDisplayListCommand;
import io.github.winnpixie.glawt.commands.impl.immediate.BeginCommand;
import io.github.winnpixie.glawt.commands.impl.immediate.ColorCommand;
import io.github.winnpixie.glawt.commands.impl.immediate.EndCommand;
import io.github.winnpixie.glawt.commands.impl.immediate.VertexCommand;
import io.github.winnpixie.glawt.commands.impl.matrix.*;
import io.github.winnpixie.glawt.commands.impl.state.LineWidthCommand;
import io.github.winnpixie.glawt.commands.impl.state.PointSizeCommand;
import io.github.winnpixie.glawt.immediate.VertexMode;
import io.github.winnpixie.glawt.matrix.Matrix;

public class OpenGL {
    private static GLContext glContext;

    private OpenGL() {
    }

    public static GLContext getGLContext() {
        return glContext;
    }

    public static void setGLContext(GLContext newContext) {
        glContext = newContext;
    }

    private static void queueCommand(GLCommand glCommand) {
        glContext.getDisplayListManager().getActiveDisplayList().add(glCommand);
    }

    public static long glGenLists(int range) {
        if (range == 0) return 0;

        long name = glContext.getDisplayListManager().createList();

        for (int i = 1; i < range; i++) glContext.getDisplayListManager().createList();

        return name;
    }

    public static void glNewList(long name, DisplayListMode mode) {
        if (name == 0) return;

        DisplayList list = new DisplayList(mode);
        glContext.getDisplayListManager().setList(name, list);
        glContext.getDisplayListManager().setActiveList(name);
    }

    public static void glEndList() {
        glContext.getDisplayListManager().setActiveList(0);
    }

    public static void glCallList(long name) {
        queueCommand(new CallDisplayListCommand(name));
    }

    public static void glCallLists(long name, int range) {
        for (int i = 0; i < range; i++) glCallList(name + i);
    }

    public static void glDeleteLists(long name, int range) {
        for (int i = 0; i < range; i++) glContext.getDisplayListManager().deleteList(name + i);
    }

    public static void glLoadMatrixf(float[] matrix) {
        double[] converted = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            converted[i] = matrix[i];
        }

        glLoadMatrixd(converted);
    }

    public static void glLoadMatrixd(double[] matrix) {
        glLoadMatrix(new Matrix(4, 4, matrix));
    }

    public static void glLoadMatrix(Matrix matrix) {
        queueCommand(new LoadMatrixCommand(matrix));
    }

    public static void glLoadIdentity() {
        queueCommand(new LoadMatrixCommand(Matrix.IDENTITY.copy()));
    }

    public static void glPushMatrix() {
        queueCommand(new PushMatrixCommand());
    }

    public static void glPopMatrix() {
        queueCommand(new PopMatrixCommand());
    }

    public static void glMultMatrixf(float[] matrix) {
        double[] converted = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            converted[i] = matrix[i];
        }

        glMultMatrixd(converted);
    }

    public static void glMultMatrixd(double[] matrix) {
        glMultMatrix(new Matrix(4, 4, matrix));
    }

    public static void glMultMatrix(Matrix matrix) {
        queueCommand(new MultiplyMatrixCommand(matrix));
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

    public static void glColor3i(int red, int green, int blue) {
        glColor4i(red, green, blue, 255);
    }

    public static void glColor3f(float red, float green, float blue) {
        glColor4f(red, green, blue, 1f);
    }

    public static void glColor3d(double red, double green, double blue) {
        glColor4d(red, green, blue, 1.0);
    }

    public static void glColor4i(int red, int green, int blue, int alpha) {
        glColor4d(red / 255.0, green / 255.0, blue / 255.0, alpha / 255.0);
    }

    public static void glColor4f(float red, float green, float blue, float alpha) {
        glColor4d(red, green, blue, alpha);
    }

    public static void glColor4d(double red, double green, double blue, double alpha) {
        queueCommand(new ColorCommand((float) red, (float) green, (float) blue, (float) alpha));
    }

    public static void glLineWidth(float size) {
        queueCommand(new LineWidthCommand(size));
    }

    public static void glPointSize(float size) {
        queueCommand(new PointSizeCommand(size));
    }

    public static void glVertex2i(int x, int y) {
        glVertex3i(x, y, 0);
    }

    public static void glVertex2f(float x, float y) {
        glVertex3f(x, y, 0);
    }

    public static void glVertex2d(double x, double y) {
        glVertex3d(x, y, 0);
    }

    public static void glVertex3i(int x, int y, int z) {
        glVertex4i(x, y, z, 1);
    }

    public static void glVertex3f(float x, float y, float z) {
        glVertex4f(x, y, z, 1);
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
        queueCommand(new VertexCommand(x, y, z, w));
    }

    public static void glBegin(VertexMode mode) {
        queueCommand(new BeginCommand(mode));
    }

    public static void glEnd() {
        queueCommand(new EndCommand());
    }
}
