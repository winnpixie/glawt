package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.Matrix;

public class MultiplyMatrixCommand implements Command {
    private final Matrix matrix;

    public MultiplyMatrixCommand(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        return true;
    }
}
