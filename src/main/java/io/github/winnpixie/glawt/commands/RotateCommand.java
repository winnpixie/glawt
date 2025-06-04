package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class RotateCommand implements Command {
    private final double angle;
    private final double x;
    private final double y;
    private final double z;

    public RotateCommand(double angle, double x, double y, double z) {
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriver().rotate(angle, x, y);

        return true;
    }
}
