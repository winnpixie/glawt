package io.github.winnpixie.glawt;

public class Matrix { // TODO: Implement
    private final int rows;
    private final int columns;

    private final double[][] values;

    public Matrix(int rows, int columns) {
        this(rows, columns, new double[rows][columns]);
    }

    public Matrix(int rows, int columns, double[][] values) {
        this.rows = rows;
        this.columns = columns;
        this.values = values;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getValues() {
        return values;
    }

    public static Matrix copy(Matrix matrix) {
        double[][] values = new double[matrix.getRows()][matrix.getColumns()];

        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getColumns(); x++) {
                values[y][x] = matrix.getValues()[y][x];
            }
        }

        return new Matrix(matrix.getRows(), matrix.getColumns(), values);
    }
}
