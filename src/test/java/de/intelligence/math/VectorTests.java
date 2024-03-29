package de.intelligence.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Suite: Vector")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VectorTests {

    private Vector vector;

    @BeforeEach
    void setUp() {
        vector = Vector.of(1, 2, 3, 4);
    }

    @Test
    void testSize() {
        assertEquals(4, vector.size());
    }

    @Test
    void testGet() {
        assertEquals(1, vector.get(0));
        assertEquals(2, vector.get(1));
        assertEquals(3, vector.get(2));
        assertEquals(4, vector.get(3));
    }

    @Test
    void testScale() {
        Vector result = vector.scale(2);
        assertEquals(1, vector.get(0));
        assertEquals(2, vector.get(1));
        assertEquals(3, vector.get(2));
        assertEquals(4, vector.get(3));
        assertEquals(2, result.get(0));
        assertEquals(4, result.get(1));
        assertEquals(6, result.get(2));
        assertEquals(8, result.get(3));
    }

    @Test
    void testScaleInPlace() {
        vector.scaleInPlace(3);
        assertEquals(3, vector.get(0));
        assertEquals(6, vector.get(1));
        assertEquals(9, vector.get(2));
        assertEquals(12, vector.get(3));
    }

    @Test
    void testAdd() {
        assertThrows(IllegalArgumentException.class, () -> vector.add(Vector.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.add(Vector.of(1, 2, 3, 4, 5)));
        Vector result = vector.add(Vector.of(2, 4, 6, 8));
        assertEquals(1, vector.get(0));
        assertEquals(2, vector.get(1));
        assertEquals(3, vector.get(2));
        assertEquals(4, vector.get(3));
        assertEquals(3, result.get(0));
        assertEquals(6, result.get(1));
        assertEquals(9, result.get(2));
        assertEquals(12, result.get(3));
    }

    @Test
    void testAddInPlace() {
        assertThrows(IllegalArgumentException.class, () -> vector.addInPlace(Vector.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.addInPlace(Vector.of(1, 2, 3, 4, 5)));
        vector.addInPlace(Vector.of(2, 4, 6, 8));
        assertEquals(3, vector.get(0));
        assertEquals(6, vector.get(1));
        assertEquals(9, vector.get(2));
        assertEquals(12, vector.get(3));
    }

    @Test
    void testMultiply() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3, 4, 5)));
        double result = vector.multiply(Vector.of(2, 4, 6, 8));
        assertEquals(60, result);
    }

    @Test
    void testPointwiseMultiply() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3, 4, 5)));
        Vector result = vector.pointwiseMultiply(Vector.of(2, 4, 6, 8));
        assertEquals(1, vector.get(0));
        assertEquals(2, vector.get(1));
        assertEquals(3, vector.get(2));
        assertEquals(4, vector.get(3));
        assertEquals(2, result.get(0));
        assertEquals(8, result.get(1));
        assertEquals(18, result.get(2));
        assertEquals(32, result.get(3));
    }

    @Test
    void testPointwiseMultiplyInPlace() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(Vector.of(1, 2, 3, 4, 5)));
        vector.pointwiseMultiplyInPlace(Vector.of(2, 4, 6, 8));
        assertEquals(2, vector.get(0));
        assertEquals(8, vector.get(1));
        assertEquals(18, vector.get(2));
        assertEquals(32, vector.get(3));
    }
}
