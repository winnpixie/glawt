package example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static io.github.winnpixie.glawt.GLAWT.glawtCreateContext;
import static io.github.winnpixie.glawt.GLAWT.glawtSetContext;
import static io.github.winnpixie.glawt.OpenGL.*;
import static io.github.winnpixie.glawt.commands.DisplayListMode.GL_COMPILE;
import static io.github.winnpixie.glawt.immediate.VertexMode.*;

public class ImageOutputExample {
    public static void main(String[] args) {
        BufferedImage output = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        glawtSetContext(glawtCreateContext(output.createGraphics()));

        // We could just work on the root command list, but why not show display lists are possible?
        long commands = glGenLists(1);
        glNewList(commands, GL_COMPILE);

        // Blank the background
        glColor4f(1f, 1f, 1f, 1f);
        glBegin(GL_QUADS);
        glVertex2d(0.0, 0.0);
        glVertex2d(128, 0.0);
        glVertex2d(128, 128);
        glVertex2d(0.0, 128);
        glEnd();

        // Draw blue 32x32 rectangles at 16, 16, and at 64, 16.
        glPushMatrix();

        glColor3f(0f, 0f, 1f);

        glBegin(GL_QUADS);
        glVertex2d(16.0, 16.0);
        glVertex2d(16.0 + 32.0, 16.0);
        glVertex2d(16.0 + 32.0, 16.0 + 32.0);
        glVertex2d(16.0, 16.0 + 32.0);
        glEnd();

        glPopMatrix();

        // Draw a red triangle at 32, 48
        glPushMatrix();

        glTranslated(16.0, 64.0, 0.0);
        glColor3f(1f, 0f, 0f);

        glBegin(GL_TRIANGLE_FAN);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 0.0);
        glVertex2d(16.0, 32.0);
        glEnd();

        glPopMatrix();

        // Draw two yellow lines at 64, 64, and 80, 64
        glPushMatrix();

        glTranslated(64.0, 64.0, 0.0);
        glColor3f(1f, 1f, 0f);

        glBegin(GL_LINES);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 32.0);

        glVertex2d(16.0, 0.0);
        glVertex2d(32.0, 0.0);
        glEnd();

        glPopMatrix();

        // Draw connected green quads at 64, 16
        glPushMatrix();

        glTranslated(64.0, 16.0, 0.0);
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

        glPushMatrix();

        glTranslated(64.0, 64.0, 0.0);
        glColor3f(1f, 0f, 1f);
        glPointSize(4f);

        glBegin(GL_POINTS);
        glVertex2d(0.0, 0.0);
        glEnd();

        glPopMatrix();

        glPushMatrix();

        glTranslated(64.0, 80.0, 0.0);
        glColor3f(1f, 0.5f, 0.0f);

        // Tessellation test
        glBegin(GL_POLYGON);
        glVertex2d(0.0, 0.0);
        glVertex2d(32.0, 0.0);
        glVertex2d(48.0, 32.0);
        glVertex2d(16.0, 16.0);
        glVertex2d(-16.0, 32.0);
        glEnd();

        glPopMatrix();

        glEndList();

        glCallList(commands); // This is necessary to execute command queue.
        glDeleteLists(commands, 1);

        try {
            ImageIO.write(output, "PNG", new File("gl-awt_example.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
