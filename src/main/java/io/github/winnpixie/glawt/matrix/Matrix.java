package io.github.winnpixie.glawt.matrix;

// TODO: Implement
public record Matrix(int rows,
                     int columns,
                     double[] values) {
    public static final Matrix IDENTITY = new Matrix(4, 4, new double[]{
            1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
    });

    public Matrix(int rows, int columns) {
        this(rows, columns, new double[rows * columns]);
    }

    public Matrix copy() {
        double[] copyValues = new double[rows * columns];
        System.arraycopy(values, 0, copyValues, 0, copyValues.length);

        return new Matrix(rows, columns, copyValues);
    }
}
