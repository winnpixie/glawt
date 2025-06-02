package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

import java.awt.*;

public class PushMatrixCommand implements Command {
    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriverStack().push((Graphics2D) context.getDriver().create());

        return true;
    }
}
