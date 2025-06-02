package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.VertexData;

public class AddVertexCommand implements Command {
    private final VertexData vertex;

    public AddVertexCommand(double x, double y) {
        this.vertex = new VertexData(x, y);
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getVertices().add(vertex);

        return true;
    }
}
