package io.github.winnpixie.glawt.commands.impl.state;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

public record PointSizeCommand(float size) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getVertexManager().setPointSize(size);

        return true;
    }
}
