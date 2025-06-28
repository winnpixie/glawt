package io.github.winnpixie.glawt.commands.impl.immediate;

import io.github.winnpixie.glawt.GLContext;
import io.github.winnpixie.glawt.commands.GLCommand;
import io.github.winnpixie.glawt.vertex.Position;
import io.github.winnpixie.glawt.vertex.Vertex;

public record VertexCommand(double x,
                            double y,
                            double z,
                            double w) implements GLCommand {
    @Override
    public boolean execute(GLContext context) {
        context.getVertexManager().getVertices().add(new Vertex(
                new Position(x, y, z, w),
                context.getVertexManager().getColor()
        ));

        return true;
    }
}
