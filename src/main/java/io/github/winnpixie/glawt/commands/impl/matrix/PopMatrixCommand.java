package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

public class PopMatrixCommand implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getGraphicsDrivers().pop();

        return true;
    }
}
