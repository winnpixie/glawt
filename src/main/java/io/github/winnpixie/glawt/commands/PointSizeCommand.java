package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class PointSizeCommand implements Command {
    private final float size;

    public PointSizeCommand(float size) {
        this.size = size;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.setPointSize(size);

        return true;
    }
}
