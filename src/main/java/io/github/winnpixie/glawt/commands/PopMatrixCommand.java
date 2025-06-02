package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;

public class PopMatrixCommand implements Command {
    @Override
    public boolean execute(GraphicsContext context) {
        context.getDriverStack().pop();

        return true;
    }
}
