package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.commands.DisplayListManager;
import io.github.winnpixie.glawt.immediate.VertexManager;
import io.github.winnpixie.glawt.matrix.MatrixManager;

import java.awt.*;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GLContext {
    private final Deque<Graphics2D> graphics = new ConcurrentLinkedDeque<>();
    private final DisplayListManager displayListManager = new DisplayListManager();
    private final MatrixManager matrixManager = new MatrixManager();
    private final VertexManager vertexManager = new VertexManager();

    GLContext() {
    }

    public Deque<Graphics2D> getGraphics() {
        return graphics;
    }

    public Graphics2D getActiveGraphics() {
        return graphics.peek();
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
}
