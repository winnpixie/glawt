package io.github.winnpixie.glawt.vertex;

public class Position {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Position(double x, double y) {
        this(x, y, 0.0);
    }

    public Position(double x, double y, double z) {
        this(x, y, z, 1.0);
    }

    public Position(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }
}
