package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class RotateCommand implements Command {
    private final double angle;
    private final double x;
    private final double y;

    public RotateCommand(double angle, double x, double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriver().rotate(angle, x, y);

        return true;
    }
}
