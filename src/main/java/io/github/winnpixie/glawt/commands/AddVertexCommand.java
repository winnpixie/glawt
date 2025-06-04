package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.Vertex;

public class AddVertexCommand implements Command {
    private final Vertex vertex;

    public AddVertexCommand(double x, double y, double z, double w) {
        this.vertex = new Vertex(x, y, z, w);
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getVertices().add(vertex);

        return true;
    }
}
