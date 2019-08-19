package de.intelligence.math;

import org.graalvm.compiler.hotspot.stubs.OutOfBoundsExceptionStub;

public class Vector {

    private double[] memory;

    public Vector(double... values) {
        memory = values;
    }

    public Vector scale(double factor) {
        Vector result = new Vector(memory.clone());
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
        Vector result = new Vector(memory.clone());
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
        Vector result = new Vector(memory.clone());
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
        return memory[index - 1];
    }

    public int size() {
        return memory.length;
    }
}
