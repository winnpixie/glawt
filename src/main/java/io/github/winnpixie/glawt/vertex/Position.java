package io.github.winnpixie.glawt.vertex;

public record Position(double x,
                       double y,
                       double z,
                       double w) {
    public Position(double x, double y) {
        this(x, y, 0.0);
    }

    public Position(double x, double y, double z) {
        this(x, y, z, 1.0);
    }
}
