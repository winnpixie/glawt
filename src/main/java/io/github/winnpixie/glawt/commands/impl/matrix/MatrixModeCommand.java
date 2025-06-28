package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.matrix.MatrixMode;
import io.github.winnpixie.glawt.commands.GLCommand;

public record MatrixModeCommand(MatrixMode mode) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getMatrixManager().setActiveMode(mode);

        return true;
    }
}
