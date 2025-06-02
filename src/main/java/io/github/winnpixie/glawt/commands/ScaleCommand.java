package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class ScaleCommand implements Command {
    private final double x;
    private final double y;

    public ScaleCommand(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriver().scale(x, y);

        return true;
    }
}
