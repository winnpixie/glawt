package io.github.winnpixie.glawt.vertex;

public record Color(float red,
                    float green,
                    float blue,
                    float alpha,
                    java.awt.Color awtColor) {
    public Color(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public Color(float red, float green, float blue, float alpha) {
        this(red, green, blue, alpha,
                new java.awt.Color(red, green, blue, alpha));
    }

    public Color lerp(Color other, float stage) {
        float redDelta = other.red() - red;
        float greenDelta = other.green() - green;
        float blueDelta = other.blue() - blue;
        float alphaDelta = other.alpha() - alpha;

        return new Color(
                red + (redDelta * stage),
                green + (greenDelta * stage),
                blue + (blueDelta * stage),
                alpha + (alphaDelta * stage)
        );
    }

    public static Color fromAWT(java.awt.Color awtColor) {
        return new Color(
                awtColor.getRed() / 255f,
                awtColor.getGreen() / 255f,
                awtColor.getBlue() / 255f,
                awtColor.getAlpha() / 255f,
                awtColor
        );
    }
}
