package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

public record TranslateCommand(double x,
                               double y,
                               double z) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        // TODO: Implement proper OpenGL math

        context.getActiveGraphics().translate(x, y);

        return true;
    }
}
