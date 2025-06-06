package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class BeginDrawCommand implements Command {
    private final byte mode;

    public BeginDrawCommand(byte mode) {
        this.mode = mode;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        if (context.getDrawMode() > -1) return false;

        context.setDrawMode(mode);

        return true;
    }
}
