package de.intelligence.math;

public class Matrix {

    private double[] memory;
    private int rows;
    private int columns;

    public Matrix(int rows, int columns) {
        memory = new double[rows * columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    public double get(int row, int column) {
        return memory[columns * row + column];
    }

    public void set(int row, int column, double value) {
        memory[columns * row + column] = value;
    }

    public Matrix scale(double factor) {
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, get(i, j) * factor);
            }
        }
        return result;
    }

    public Matrix scaleInPlace(double factor) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                set(i, j, get(i, j) * factor);
            }
        }
        return this;
    }

    public Matrix add(Matrix rhs) {
        if (rows != rhs.rows || columns != rhs.columns)
            throw new IllegalArgumentException("Matrix dimensions for addition does not match");
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, get(i, j) + rhs.get(i, j));
            }
        }
        return result;
    }

    public Matrix addInPlace(Matrix rhs) {
        if (rows != rhs.rows || columns != rhs.columns)
            throw new IllegalArgumentException("Matrix dimensions for addition does not match");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                set(i, j, get(i, j) + rhs.get(i, j));
            }
        }
        return this;
    }

    public Matrix multiply(Matrix rhs) {
        if (columns != rhs.rows)
            throw new IllegalArgumentException("Matrix dimensions for addition does not match");
        Matrix result = new Matrix(rows, rhs.columns);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                double sum = 0;
                for (int k = 0; k < rhs.rows; k++) {
                    sum += get(i, k) * rhs.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Vector multiply(Vector rhs) {
        if (columns != rhs.size())
            throw new IllegalArgumentException("Column dimension of matrix does not match dimension of given vector for matrix-vector multiplication");
        Vector result = new Vector(rows);
        for (int i = 0; i < rows; i++) {
            double sum = 0;
            for (int j = 0; j < columns; j++) {
                sum += get(i, j) * rhs.get(j);
            }
            result.set(i, sum);
        }
        return result;
    }

    public Matrix pointwiseMultiply(Matrix rhs) {
        if (columns != rhs.columns || rows != rhs.rows)
            throw new IllegalArgumentException("Matrix dimensions for addition does not match");
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, get(i, j) * rhs.get(i, j));
            }
        }
        return result;
    }

    public Matrix pointwiseMultiplyInPlace(Matrix rhs) {
        if (columns != rhs.columns || rows != rhs.rows)
            throw new IllegalArgumentException("Matrix dimensions for addition does not match");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                set(i, j, get(i, j) * rhs.get(i, j));
            }
        }
        return this;
    }
}
