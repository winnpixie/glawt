package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

public record RotateCommand(double angle,
                            double x,
                            double y,
                            double z) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        // TODO: Implement proper OpenGL math

        context.getActiveDriver().rotate(angle, x, y);

        return true;
    }
}
