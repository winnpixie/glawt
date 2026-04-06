package example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static io.github.winnpixie.glawt.GLAWT.ShapeMode.GLAWT_SHAPES_CONVEX;
import static io.github.winnpixie.glawt.GLAWT.*;
import static io.github.winnpixie.glawt.OpenGL.*;
import static io.github.winnpixie.glawt.commands.DisplayListMode.GL_COMPILE;
import static io.github.winnpixie.glawt.immediate.VertexMode.*;

public class ImageOutputExample {
    public static void main(String[] args) {
        BufferedImage output = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        glawtSetContext(glawtCreateContext(output.createGraphics()));

        // Display List showcase
        long mainCommands = glGenLists(2); // Generate 2 lists, the second will be used later in this example
        glNewList(mainCommands, GL_COMPILE);

        // Background
        drawBackground();

        // Vertex Modes
        drawQuad();
        drawTriangle();
        drawLines();
        drawPoint();
        drawQuads();
        drawPolygon();

        glEndList();

        // Set-up to for shape mode showcase
        long weirdShape = mainCommands + 1;
        glNewList(weirdShape, GL_COMPILE);

        drawStrange();

        glEndList();

        glCallList(mainCommands); // This is necessary to execute command queue.

        glawtSetShapeMode(GLAWT_SHAPES_CONVEX);
        glCallList(weirdShape);

        glDeleteLists(mainCommands, 2); // Delete both lists from memory

        try {
            ImageIO.write(output, "PNG", new File("gl-awt_example.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void drawBackground() {
        // Blank the background
        glColor4f(1f, 1f, 1f, 1f);

        glBegin(GL_QUADS);
        glVertex2d(0.0, 0.0);
        glVertex2d(128, 0.0);
        glVertex2d(128, 128);
        glVertex2d(0.0, 128);
        glEnd();
    }

    private static void drawQuad() {
        // Draw blue 32x32 rectangles at 8, 8
        glPushMatrix();

        glTranslated(8.0, 8.0, 0.0);
        glColor3f(0f, 0f, 1f);

        glBegin(GL_QUADS);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 0.0);
        glVertex2d(32.0, 32.0);
        glVertex2d(0.0, 32.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawTriangle() {
        // Draw a red triangle at 8, 48
        glPushMatrix();

        glTranslated(8.0, 48.0, 0.0);
        glColor3f(1f, 0f, 0f);

        glBegin(GL_TRIANGLE_FAN);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 0.0);
        glVertex2d(16.0, 32.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawLines() {
        // Draw two yellow lines at 8, 88, and 24, 88
        glPushMatrix();

        glTranslated(8.0, 88.0, 0.0);
        glColor3f(1f, 1f, 0f);

        glBegin(GL_LINES);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 32.0);

        glVertex2d(16.0, 0.0);
        glVertex2d(32.0, 0.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawQuads() {
        // Draw connected green quads at 72, 8
        glPushMatrix();

        glTranslated(72.0, 8.0, 0.0);
        glColor3f(0f, 1f, 0f);

        glBegin(GL_QUAD_STRIP);
        glVertex2d(0.0, 0.0);
        glVertex2d(0.0, 16.0);

        glVertex2d(16.0, 16.0);
        glVertex2d(16.0, 0.0);

        glVertex2d(32.0, 16.0);
        glVertex2d(32.0, 32.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawPoint() {
        // Draw a magenta point at 64, 64
        glPushMatrix();

        glTranslated(64.0, 64.0, 0.0);
        glColor3f(1f, 0f, 1f);
        glPointSize(4f);

        glBegin(GL_POINTS);
        glVertex2d(0.0, 0.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawPolygon() {
        // Draw an orange convex polygon
        glPushMatrix();

        glTranslated(72.0, 88.0, 0.0);
        glColor3f(1f, 0.5f, 0.0f);

        glBegin(GL_POLYGON);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 0.0);
        glVertex2d(48.0, 32.0);
        glVertex2d(16.0, 16.0);
        glVertex2d(-16.0, 32.0);
        glEnd();

        glPopMatrix();
    }

    private static void drawStrange() {
        // Draw a strange cyan polygon at 72,
        glPushMatrix();

        glTranslated(72.0, 64.0, 0.0);
        glColor3f(0f, 1f, 1f);

        glBegin(GL_POLYGON);
        glVertex2d(0.0, 0.0);
        glVertex2d(16.0, 16.0);
        glVertex2d(16.0, -16.0);
        glVertex2d(32.0, 0.0);
        glEnd();

        glPopMatrix();
    }
}
