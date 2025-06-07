package io.github.winnpixie.glawt.vertex;

public class Vertex {
    private final Position position;
    private final Color color;

    public Vertex(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
