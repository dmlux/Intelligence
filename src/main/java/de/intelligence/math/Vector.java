package de.intelligence.math;

public class Vector {

    private double[] memory;

    public static Vector of(double... values) {
        Vector result = new Vector(values.length);
        for (int i = 0; i < values.length; i++) {
            result.set(i, values[i]);
        }
        return result;
    }

    public Vector(int size) {
        memory = new double[size];
    }

    public Vector scale(double factor) {
        Vector result = new Vector(memory.length);
        result.memory = memory.clone();
        for (int i = 0; i < result.memory.length; i++)
            result.memory[i] *= factor;
        return result;
    }

    public Vector scaleInPlace(double factor) {
        for (int i = 0; i < memory.length; i++)
            memory[i] *= factor;
        return this;
    }

    public Vector add(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for vector addition do not match");
        Vector result = new Vector(memory.length);
        result.memory = memory.clone();
        for (int i = 0; i < result.memory.length; i++)
            result.memory[i] += rhs.memory[i];
        return result;
    }

    public Vector addInPlace(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for vector in-place addition do not match");
        for (int i = 0; i < memory.length; i++)
            memory[i] += rhs.memory[i];
        return this;
    }

    public Vector sub(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for vector addition do not match");
        Vector result = new Vector(memory.length);
        result.memory = memory.clone();
        for (int i = 0; i < result.memory.length; i++)
            result.memory[i] -= rhs.memory[i];
        return result;
    }

    public Vector subInPlace(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for vector in-place addition do not match");
        for (int i = 0; i < memory.length; i++)
            memory[i] -= rhs.memory[i];
        return this;
    }

    public double multiply(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for vector multiplication do not match");
        double result = 0;
        for (int i = 0; i < memory.length; i++)
            result += memory[i] * rhs.memory[i];
        return result;
    }

    public Vector pointwiseMultiply(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for pointwise vector multiplication do not match");
        Vector result = new Vector(memory.length);
        result.memory = memory.clone();
        for (int i = 0; i < memory.length; i++)
            result.memory[i] *= rhs.memory[i];
        return result;
    }

    public Vector pointwiseMultiplyInPlace(Vector rhs) {
        if (rhs.memory.length != memory.length)
            throw new IllegalArgumentException("Dimensions for pointwise vector in-place multiplication do not match");
        for (int i = 0; i < memory.length; i++)
            memory[i] *= rhs.memory[i];
        return this;
    }

    public double get(int index) {
        if (index > memory.length)
            throw new IndexOutOfBoundsException();
        return memory[index];
    }

    public void set(int index, double value) {
        memory[index] = value;
    }

    public int size() {
        return memory.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (int i = 0; i < memory.length; i++) {
            if (i < memory.length - 1) {
                stringBuilder.append(String.format("%.4f", memory[i])).append(", ");
            } else {
                stringBuilder.append(String.format("%.4f", memory[i])).append(")");
            }
        }
        return stringBuilder.toString();
    }
}
