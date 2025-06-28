package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.matrix.Matrix;
import io.github.winnpixie.glawt.commands.GLCommand;

public record MultiplyMatrixCommand(Matrix matrix) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        // TODO: Implement
        return true;
    }
}
