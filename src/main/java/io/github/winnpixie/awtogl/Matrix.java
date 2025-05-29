package io.github.winnpixie.awtogl;

import java.awt.*;

public class Matrix {
    private final Graphics2D graphics;

    public Matrix(Matrix context) {
        this((Graphics2D) context.graphics.create());
    }

    public Matrix(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }
}
