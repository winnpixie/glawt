package io.github.winnpixie.glawt.commands.impl.immediate;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.vertex.Color;

public record ColorCommand(float red,
                           float green,
                           float blue,
                           float alpha) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getVertexManager().setColor(new Color(red, green, blue, alpha));

        return true;
    }
}
