package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.DrawMode;
import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class BeginDrawCommand implements Command {
    private final DrawMode drawMode;

    public BeginDrawCommand(DrawMode drawMode) {
        this.drawMode = drawMode;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        if (context.getDrawMode() != null) return false;

        context.setDrawMode(drawMode);

        return true;
    }
}
