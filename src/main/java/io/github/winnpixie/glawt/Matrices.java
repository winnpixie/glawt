package io.github.winnpixie.glawt;

public class Matrices {
    public static final Matrix IDENTITY = new Matrix(4, 4, new double[][]{
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
    });

    private static Matrix modelViewMatrix = Matrix.copy(IDENTITY);
    private static Matrix projectionMatrix = Matrix.copy(IDENTITY);
    private static Matrix textureMatrix = Matrix.copy(IDENTITY);
    private static Matrix colorMatrix = Matrix.copy(IDENTITY);
}
