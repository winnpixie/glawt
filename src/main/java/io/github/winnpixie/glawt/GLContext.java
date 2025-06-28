package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.commands.DisplayListManager;
import io.github.winnpixie.glawt.immediate.VertexManager;
import io.github.winnpixie.glawt.matrix.MatrixManager;

import java.awt.*;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GLContext {
    private final Deque<Graphics2D> graphicsDrivers = new ConcurrentLinkedDeque<>();
    private final DisplayListManager displayListManager = new DisplayListManager();
    private final MatrixManager matrixManager = new MatrixManager();
    private final VertexManager vertexManager = new VertexManager();

    private GLContext() {
    }

    public Deque<Graphics2D> getGraphicsDrivers() {
        return graphicsDrivers;
    }

    public Graphics2D getActiveDriver() {
        return graphicsDrivers.peek();
    }

    public DisplayListManager getDisplayListManager() {
        return displayListManager;
    }

    public MatrixManager getMatrixManager() {
        return matrixManager;
    }

    public VertexManager getVertexManager() {
        return vertexManager;
    }

    public static GLContext create(Graphics2D graphicsDriver) {
        GLContext context = new GLContext();
        context.getGraphicsDrivers().push(graphicsDriver);

        return context;
    }
}
