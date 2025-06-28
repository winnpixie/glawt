package io.github.winnpixie.glawt.commands.impl.immediate;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.immediate.VertexMode;

public class EndCommand implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        if (context.getVertexManager().getMode() == VertexMode.NOTHING) return false;

        java.awt.Color awtColor = context.getActiveDriver().getColor();

        context.getVertexManager().draw(context);
        context.getVertexManager().getVertices().clear();
        context.getVertexManager().setMode(VertexMode.NOTHING);

        // Restore color
        context.getActiveDriver().setColor(awtColor);
        return true;
    }
}
