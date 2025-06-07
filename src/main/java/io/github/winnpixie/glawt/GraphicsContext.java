package io.github.winnpixie.glawt;

import io.github.winnpixie.glawt.vertex.Vertex;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphicsContext {
    private final Stack<Graphics2D> driverStack = new Stack<>();
    private final List<Vertex> vertices = new ArrayList<>();

    private byte drawMode = -1;
    private float pointSize = 1f;

    public GraphicsContext(Graphics2D driver) {
        driverStack.push(driver); // Push an initial state
    }

    public Stack<Graphics2D> getDriverStack() {
        return driverStack;
    }

    public Graphics2D getDriver() {
        return driverStack.peek();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public byte getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(byte mode) {
        this.drawMode = mode;
    }

    public float getPointSize() {
        return pointSize;
    }

    public void setPointSize(float pointSize) {
        this.pointSize = pointSize;
    }
}
