package io.github.winnpixie.glawt.commands.impl.matrix;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;

import java.awt.*;

public class PushMatrixCommand implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getGraphics().push((Graphics2D) context.getActiveGraphics().create());

        return true;
    }
}
