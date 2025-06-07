package io.github.winnpixie.glawt.commands;

import io.github.winnpixie.glawt.Command;
import io.github.winnpixie.glawt.GraphicsContext;
import io.github.winnpixie.glawt.vertex.Color;
import io.github.winnpixie.glawt.vertex.Position;
import io.github.winnpixie.glawt.vertex.Vertex;

public class AddVertexCommand implements Command {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public AddVertexCommand(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public boolean execute(GraphicsContext context) {
        context.getVertices().add(new Vertex(
                new Position(x, y, z, w),
                Color.fromAWT(context.getDriver().getColor())
        ));

        return true;
    }
}
