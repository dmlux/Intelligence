package de.intelligence.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Suite: Matrix")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatrixTests {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
        matrix = new Matrix(2, 3);
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 0, 2);
        matrix.set(1, 1, 4);
        matrix.set(1, 2, 8);
    }

    @Test
    void testScale() {
        Matrix result = matrix.scale(3);
        assertEquals(matrix.get(0, 0), 1);
        assertEquals(matrix.get(0, 1), 2);
        assertEquals(matrix.get(0, 2), 3);
        assertEquals(matrix.get(1, 0), 2);
        assertEquals(matrix.get(1, 1), 4);
        assertEquals(matrix.get(1, 2), 8);

        assertEquals(result.get(0, 0), 3);
        assertEquals(result.get(0, 1), 6);
        assertEquals(result.get(0, 2), 9);
        assertEquals(result.get(1, 0), 6);
        assertEquals(result.get(1, 1), 12);
        assertEquals(result.get(1, 2), 24);
    }

    @Test
    void testScaleInPlace() {
        matrix.scaleInPlace(3);
        assertEquals(matrix.get(0, 0), 3);
        assertEquals(matrix.get(0, 1), 6);
        assertEquals(matrix.get(0, 2), 9);
        assertEquals(matrix.get(1, 0), 6);
        assertEquals(matrix.get(1, 1), 12);
        assertEquals(matrix.get(1, 2), 24);
    }

    @Test
    void testAdd() {
        assertThrows(IllegalArgumentException.class, () -> matrix.add(new Matrix(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.add(new Matrix(3, 3)));

        Matrix operand = new Matrix(2, 3);
        operand.set(0, 0, 2);
        operand.set(0, 1, 15);
        operand.set(0, 2, 84);
        operand.set(1, 0, 19);
        operand.set(1, 1, 13);
        operand.set(1, 2, 38);

        Matrix result = matrix.add(operand);

        assertEquals(matrix.get(0, 0), 1);
        assertEquals(matrix.get(0, 1), 2);
        assertEquals(matrix.get(0, 2), 3);
        assertEquals(matrix.get(1, 0), 2);
        assertEquals(matrix.get(1, 1), 4);
        assertEquals(matrix.get(1, 2), 8);

        assertEquals(result.get(0, 0), 3);
        assertEquals(result.get(0, 1), 17);
        assertEquals(result.get(0, 2), 87);
        assertEquals(result.get(1, 0), 21);
        assertEquals(result.get(1, 1), 17);
        assertEquals(result.get(1, 2), 46);
    }

    @Test
    void testAddInPlace() {
        assertThrows(IllegalArgumentException.class, () -> matrix.addInPlace(new Matrix(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.addInPlace(new Matrix(3, 3)));

        Matrix operand = new Matrix(2, 3);
        operand.set(0, 0, 2);
        operand.set(0, 1, 15);
        operand.set(0, 2, 84);
        operand.set(1, 0, 19);
        operand.set(1, 1, 13);
        operand.set(1, 2, 38);

        matrix.addInPlace(operand);

        assertEquals(matrix.get(0, 0), 3);
        assertEquals(matrix.get(0, 1), 17);
        assertEquals(matrix.get(0, 2), 87);
        assertEquals(matrix.get(1, 0), 21);
        assertEquals(matrix.get(1, 1), 17);
        assertEquals(matrix.get(1, 2), 46);
    }

    @Test
    void testMultiply() {
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(new Matrix(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(new Matrix(4, 2)));

        Matrix operand = new Matrix(3, 4);
        operand.set(0, 0, 2);
        operand.set(0, 1, 4);
        operand.set(0, 2, 6);
        operand.set(0, 3, 8);
        operand.set(1, 0, 3);
        operand.set(1, 1, 6);
        operand.set(1, 2, 9);
        operand.set(1, 3, 12);
        operand.set(2, 0, 4);
        operand.set(2, 1, 8);
        operand.set(2, 2, 12);
        operand.set(2, 3, 16);

        Matrix result = matrix.multiply(operand);

        assertEquals(matrix.get(0, 0), 1);
        assertEquals(matrix.get(0, 1), 2);
        assertEquals(matrix.get(0, 2), 3);
        assertEquals(matrix.get(1, 0), 2);
        assertEquals(matrix.get(1, 1), 4);
        assertEquals(matrix.get(1, 2), 8);

        assertEquals(4, result.columns());
        assertEquals(2, result.rows());

        assertEquals(20, result.get(0, 0));
        assertEquals(40, result.get(0, 1));
        assertEquals(60, result.get(0, 2));
        assertEquals(80, result.get(0, 3));
        assertEquals(48, result.get(1, 0));
        assertEquals(96, result.get(1, 1));
        assertEquals(144, result.get(1, 2));
        assertEquals(192, result.get(1, 3));
    }

    @Test
    void testMultiplyVec() {
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(new Vector(2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.multiply(new Vector(4)));

        Vector result = matrix.multiply(Vector.of(3, 6, 9));

        assertEquals(matrix.get(0, 0), 1);
        assertEquals(matrix.get(0, 1), 2);
        assertEquals(matrix.get(0, 2), 3);
        assertEquals(matrix.get(1, 0), 2);
        assertEquals(matrix.get(1, 1), 4);
        assertEquals(matrix.get(1, 2), 8);

        assertEquals(42, result.get(0));
        assertEquals(102, result.get(1));
    }

    @Test
    void pointwiseMultiply() {
        assertThrows(IllegalArgumentException.class, () -> matrix.pointwiseMultiply(new Matrix(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.pointwiseMultiply(new Matrix(3, 3)));

        Matrix operand = new Matrix(2, 3);
        operand.set(0, 0, 8);
        operand.set(0, 1, 4);
        operand.set(0, 2, 2);
        operand.set(1, 0, 3);
        operand.set(1, 1, 2);
        operand.set(1, 2, 1);

        Matrix result = matrix.pointwiseMultiply(operand);

        assertEquals(matrix.get(0, 0), 1);
        assertEquals(matrix.get(0, 1), 2);
        assertEquals(matrix.get(0, 2), 3);
        assertEquals(matrix.get(1, 0), 2);
        assertEquals(matrix.get(1, 1), 4);
        assertEquals(matrix.get(1, 2), 8);

        assertEquals(result.get(0, 0), 8);
        assertEquals(result.get(0, 1), 8);
        assertEquals(result.get(0, 2), 6);
        assertEquals(result.get(1, 0), 6);
        assertEquals(result.get(1, 1), 8);
        assertEquals(result.get(1, 2), 8);
    }

    @Test
    void pointwiseMultiplyInPlace() {
        assertThrows(IllegalArgumentException.class, () -> matrix.pointwiseMultiply(new Matrix(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> matrix.pointwiseMultiply(new Matrix(3, 3)));

        Matrix operand = new Matrix(2, 3);
        operand.set(0, 0, 8);
        operand.set(0, 1, 4);
        operand.set(0, 2, 2);
        operand.set(1, 0, 3);
        operand.set(1, 1, 2);
        operand.set(1, 2, 1);

        matrix.pointwiseMultiplyInPlace(operand);


        assertEquals(matrix.get(0, 0), 8);
        assertEquals(matrix.get(0, 1), 8);
        assertEquals(matrix.get(0, 2), 6);
        assertEquals(matrix.get(1, 0), 6);
        assertEquals(matrix.get(1, 1), 8);
        assertEquals(matrix.get(1, 2), 8);
    }
}
