package io.github.winnpixie.glawt.commands.impl.immediate;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.immediate.VertexMode;

public record BeginCommand(VertexMode mode) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        if (context.getVertexManager().getMode() != VertexMode.NOTHING) return false;

        context.getVertexManager().setMode(mode);
        return true;
    }
}
