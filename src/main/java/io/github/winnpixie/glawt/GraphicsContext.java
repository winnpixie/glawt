package io.github.winnpixie.glawt;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphicsContext {
    private final Stack<Graphics2D> driverStack = new Stack<>();
    private final List<VertexData> vertices = new ArrayList<>();

    private DrawMode drawMode;

    public GraphicsContext(Graphics2D driver) {
        driverStack.push(driver); // Push an initial state
    }

    public Stack<Graphics2D> getDriverStack() {
        return driverStack;
    }

    public Graphics2D getDriver() {
        return driverStack.peek();
    }

    public List<VertexData> getVertices() {
        return vertices;
    }

    public DrawMode getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DrawMode drawMode) {
        this.drawMode = drawMode;
    }
}
