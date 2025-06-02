package io.github.winnpixie.glawt;

public class VertexData {
    private final double x;
    private final double y;
    private final int[] color = new int[4]; // TODO: "Implement" per-vertex coloring (maybe?)

    public VertexData(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int[] getColor() {
        return color;
    }
}
