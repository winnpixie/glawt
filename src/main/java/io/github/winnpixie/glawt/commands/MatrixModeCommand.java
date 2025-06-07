package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.Matrices;

public class MatrixModeCommand implements Command {
    private final byte mode;

    public MatrixModeCommand(byte mode) {
        this.mode = mode;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        Matrices.setActiveMatrix(mode);

        return true;
    }
}
