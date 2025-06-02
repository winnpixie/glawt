package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

import java.awt.*;

public class LineWidthCommand implements Command {
    private final float size;

    public LineWidthCommand(float size) {
        this.size = size;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriver().setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

        return true;
    }
}
