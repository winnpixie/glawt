package io.github.winnpixie.awtogl;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GL {
    private static final Stack<Matrix> MATRIX_STACK = new Stack<>();
    private static final List<Vertex2D> VERTEX_ARRAY = new ArrayList<>();

    private static Matrix context;
    private static DrawMode drawMode;

    public static void setContext(Matrix ctx) {
        context = MATRIX_STACK.push(ctx);
    }

    public static void glPushMatrix() {
        context = MATRIX_STACK.push(new Matrix(context));
    }

    public static void glPopMatrix() {
        if (MATRIX_STACK.size() < 2) throw new RuntimeException("GL_STACK_UNDERFLOW");

        MATRIX_STACK.pop();
        context = MATRIX_STACK.peek();
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
        context.getGraphics().setColor(new Color(red, green, blue, alpha));
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
        context.getGraphics().translate(x, y);
    }

    public static void glScalef(float scaleX, float scaleY) {
        glScaled(scaleX, scaleY);
    }

    public static void glScaled(double scaleX, double scaleY) {
        context.getGraphics().scale(scaleX, scaleY);
    }

    public static void glLineWidth(float size) {
        context.getGraphics().setStroke(new BasicStroke(size));
    }

    public static void glVertex2i(int x, int y) {
        glVertex2d(x, y);
    }

    public static void glVertex2f(float x, float y) {
        glVertex2d(x, y);
    }

    public static void glVertex2d(double x, double y) {
        VERTEX_ARRAY.add(new Vertex2D(x, y));
    }

    public static void glBegin(int drawMode) {
        glBegin(DrawMode.fromGLInt(drawMode));
    }

    public static void glBegin(DrawMode drawMode) {
        if (GL.drawMode != null) throw new RuntimeException("glBegin(GLenum) executed before glEnd() has been called.");

        GL.drawMode = drawMode;
    }

    public static void glEnd() {
        switch (drawMode) {
            case GL_POINTS: {
                for (Vertex2D vertex : VERTEX_ARRAY) {
                    context.getGraphics().fill(new Rectangle2D.Double(
                            vertex.getX(),
                            vertex.getY(),
                            1.0,
                            1.0));
                }
                break;
            }

            case GL_LINES: {
                for (int i = 0; i < VERTEX_ARRAY.size(); i += 2) {
                    Vertex2D start = VERTEX_ARRAY.get(i);
                    Vertex2D end = VERTEX_ARRAY.get(i + 1);

                    Path2D.Double linePath = new Path2D.Double();
                    linePath.moveTo(start.getX(), start.getY());
                    linePath.lineTo(end.getX(), end.getY());

                    context.getGraphics().draw(linePath);
                }
                break;
            }

            case GL_LINE_STRIP: {
                Vertex2D start = VERTEX_ARRAY.get(0);

                for (int i = 1; i < VERTEX_ARRAY.size(); i++) {
                    Vertex2D end = VERTEX_ARRAY.get(i);

                    Path2D.Double linePath = new Path2D.Double();
                    linePath.moveTo(start.getX(), start.getY());
                    linePath.lineTo(end.getX(), end.getY());

                    context.getGraphics().draw(linePath);

                    start = end;
                }
                break;
            }

            case GL_LINE_LOOP: {
                Vertex2D origin = VERTEX_ARRAY.get(0);
                VERTEX_ARRAY.add(origin);
                Vertex2D start = origin;

                for (int i = 1; i < VERTEX_ARRAY.size(); i++) {
                    Vertex2D end = VERTEX_ARRAY.get(i);

                    Path2D.Double linePath = new Path2D.Double();
                    linePath.moveTo(start.getX(), start.getY());
                    linePath.lineTo(end.getX(), end.getY());

                    context.getGraphics().draw(linePath);

                    start = end;
                }
                break;
            }

            case GL_TRIANGLES: {
                for (int i = 0; i < VERTEX_ARRAY.size(); i += 3) {
                    Vertex2D cornerOne = VERTEX_ARRAY.get(i);
                    Vertex2D cornerTwo = VERTEX_ARRAY.get(i + 1);
                    Vertex2D cornerThree = VERTEX_ARRAY.get(i + 2);

                    Path2D.Double trianglePath = new Path2D.Double();
                    trianglePath.moveTo(cornerOne.getX(), cornerOne.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());

                    context.getGraphics().fill(trianglePath);
                }
                break;
            }

            case GL_TRIANGLE_STRIP: {
                Vertex2D cornerOne = VERTEX_ARRAY.get(0);

                for (int i = 1; i < VERTEX_ARRAY.size(); i += 2) {
                    Vertex2D cornerTwo = VERTEX_ARRAY.get(i);
                    Vertex2D cornerThree = VERTEX_ARRAY.get(i + 1);

                    Path2D.Double trianglePath = new Path2D.Double();
                    trianglePath.moveTo(cornerOne.getX(), cornerOne.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());

                    context.getGraphics().fill(trianglePath);

                    cornerOne = cornerThree;
                }
                break;
            }

            case GL_TRIANGLE_FAN: {
                Vertex2D origin = VERTEX_ARRAY.get(0);

                for (int i = 1; i < VERTEX_ARRAY.size(); i += 2) {
                    Vertex2D cornerTwo = VERTEX_ARRAY.get(i);
                    Vertex2D cornerThree = VERTEX_ARRAY.get(i + 1);

                    Path2D.Double trianglePath = new Path2D.Double();
                    trianglePath.moveTo(origin.getX(), origin.getY());
                    trianglePath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    trianglePath.lineTo(cornerThree.getX(), cornerThree.getY());

                    context.getGraphics().fill(trianglePath);
                }
                break;
            }

            case GL_QUADS: {
                for (int i = 0; i < VERTEX_ARRAY.size(); i += 4) {
                    Vertex2D cornerOne = VERTEX_ARRAY.get(i);
                    Vertex2D cornerTwo = VERTEX_ARRAY.get(i + 1);
                    Vertex2D cornerThree = VERTEX_ARRAY.get(i + 2);
                    Vertex2D cornerFour = VERTEX_ARRAY.get(i + 3);

                    Path2D.Double quadPath = new Path2D.Double();
                    quadPath.moveTo(cornerOne.getX(), cornerOne.getY());
                    quadPath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    quadPath.lineTo(cornerThree.getX(), cornerThree.getY());
                    quadPath.lineTo(cornerFour.getX(), cornerFour.getY());

                    context.getGraphics().fill(quadPath);
                }
                break;
            }

            case GL_QUAD_STRIP: {
                Vertex2D cornerOne = VERTEX_ARRAY.get(0);
                Vertex2D cornerTwo = VERTEX_ARRAY.get(1);

                for (int i = 0; i < VERTEX_ARRAY.size(); i += 2) {
                    Vertex2D cornerThree = VERTEX_ARRAY.get(i + 2);
                    Vertex2D cornerFour = VERTEX_ARRAY.get(i + 3);

                    Path2D.Double quadPath = new Path2D.Double();
                    quadPath.moveTo(cornerOne.getX(), cornerOne.getY());
                    quadPath.lineTo(cornerTwo.getX(), cornerTwo.getY());
                    quadPath.lineTo(cornerThree.getX(), cornerThree.getY());
                    quadPath.lineTo(cornerFour.getX(), cornerFour.getY());

                    context.getGraphics().fill(quadPath);

                    cornerOne = cornerThree;
                    cornerTwo = cornerFour;
                }
                break;
            }

            case GL_POLYGON: {
                // TODO: Implement GL_POLYGON
                break;
            }
        }

        VERTEX_ARRAY.clear();
        drawMode = null;
    }
}
