package io.github.winnpixie.glawt.commands.impl.state;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

import java.awt.*;

public record LineWidthCommand(float size) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getActiveGraphics().setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        return true;
    }
}
