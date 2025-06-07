package io.github.winnpixie.glawt.vertex;

public class Color {
    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    private final java.awt.Color awtColor;

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;

        this.awtColor = new java.awt.Color(red, green, blue, alpha);
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public Color lerp(Color other, float stage) {
        float redDelta = other.getRed() - red;
        float greenDelta = other.getRed() - green;
        float blueDelta = other.getRed() - blue;
        float alphaDelta = other.getRed() - alpha;

        return new Color(
                red + (redDelta * stage),
                green + (greenDelta * stage),
                blue + (blueDelta * stage),
                alpha + (alphaDelta * stage)
        );
    }

    public java.awt.Color getAwtColor() {
        return awtColor;
    }

    public static Color fromAWT(java.awt.Color awtColor) {
        return new Color(
                awtColor.getRed() / 255f,
                awtColor.getGreen() / 255f,
                awtColor.getBlue() / 255f,
                awtColor.getAlpha() / 255f
        );
    }
}
