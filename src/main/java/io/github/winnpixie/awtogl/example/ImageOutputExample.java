package io.github.winnpixie.awtogl.example;

import io.github.winnpixie.awtogl.DrawMode;
import io.github.winnpixie.awtogl.GL;
import io.github.winnpixie.awtogl.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageOutputExample {
    public static void main(String[] args) {
        BufferedImage output = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
        GL.setContext(new Matrix(output.createGraphics()));

        // Blank the background
        GL.glColor4f(1f, 1f, 1f, 1f);
        GL.glBegin(DrawMode.GL_QUADS);
        GL.glVertex2d(0, 0);
        GL.glVertex2d(128, 0);
        GL.glVertex2d(128, 128);
        GL.glVertex2d(0, 128);
        GL.glEnd();

        // Draw a blue rectangle at 16, 16
        GL.glPushMatrix();
        GL.glColor3f(0f, 0f, 1f);
        GL.glTranslated(16.0, 16.0);

        GL.glBegin(DrawMode.GL_QUADS);
        GL.glVertex2d(0, 0);
        GL.glVertex2d(32, 0);
        GL.glVertex2d(32, 32);
        GL.glVertex2d(0, 32);
        GL.glEnd();
        GL.glPopMatrix();

        // Draw a red triangle at 16, 48
        GL.glColor3f(1f, 0f, 0f);
        GL.glTranslated(16.0, 48.0);

        GL.glBegin(DrawMode.GL_TRIANGLE_FAN);
        GL.glVertex2d(0.0, 0.0);
        GL.glVertex2d(32, 0.0);
        GL.glVertex2d(16, 64);
        GL.glEnd();

        try {
            ImageIO.write(output, "PNG", new File("awtogl-example.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
