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
        vector = new Vector(1, 2, 3, 4);
    }

    @Test
    void testSize() {
        assertEquals(4, vector.size());
    }

    @Test
    void testGet() {
        assertEquals(1, vector.get(1));
        assertEquals(2, vector.get(2));
        assertEquals(3, vector.get(3));
        assertEquals(4, vector.get(4));
    }

    @Test
    void testScale() {
        Vector result = vector.scale(2);
        assertEquals(1, vector.get(1));
        assertEquals(2, vector.get(2));
        assertEquals(3, vector.get(3));
        assertEquals(4, vector.get(4));
        assertEquals(2, result.get(1));
        assertEquals(4, result.get(2));
        assertEquals(6, result.get(3));
        assertEquals(8, result.get(4));
    }

    @Test
    void testScaleInPlace() {
        vector.scaleInPlace(3);
        assertEquals(3, vector.get(1));
        assertEquals(6, vector.get(2));
        assertEquals(9, vector.get(3));
        assertEquals(12, vector.get(4));
    }

    @Test
    void testAdd() {
        assertThrows(IllegalArgumentException.class, () -> vector.add(new Vector(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.add(new Vector(1, 2, 3, 4, 5)));
        Vector result = vector.add(new Vector(2, 4, 6, 8));
        assertEquals(1, vector.get(1));
        assertEquals(2, vector.get(2));
        assertEquals(3, vector.get(3));
        assertEquals(4, vector.get(4));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
        assertEquals(9, result.get(3));
        assertEquals(12, result.get(4));
    }

    @Test
    void testAddInPlace() {
        assertThrows(IllegalArgumentException.class, () -> vector.addInPlace(new Vector(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.addInPlace(new Vector(1, 2, 3, 4, 5)));
        vector.addInPlace(new Vector(2, 4, 6, 8));
        assertEquals(3, vector.get(1));
        assertEquals(6, vector.get(2));
        assertEquals(9, vector.get(3));
        assertEquals(12, vector.get(4));
    }

    @Test
    void testMultiply() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3, 4, 5)));
        double result = vector.multiply(new Vector(2, 4, 6, 8));
        assertEquals(60, result);
    }

    @Test
    void testPointwiseMultiply() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3, 4, 5)));
        Vector result = vector.pointwiseMultiply(new Vector(2, 4, 6, 8));
        assertEquals(1, vector.get(1));
        assertEquals(2, vector.get(2));
        assertEquals(3, vector.get(3));
        assertEquals(4, vector.get(4));
        assertEquals(2, result.get(1));
        assertEquals(8, result.get(2));
        assertEquals(18, result.get(3));
        assertEquals(32, result.get(4));
    }

    @Test
    void testPointwiseMultiplyInPlace() {
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3)));
        assertThrows(IllegalArgumentException.class, () -> vector.multiply(new Vector(1, 2, 3, 4, 5)));
        vector.pointwiseMultiplyInPlace(new Vector(2, 4, 6, 8));
        assertEquals(2, vector.get(1));
        assertEquals(8, vector.get(2));
        assertEquals(18, vector.get(3));
        assertEquals(32, vector.get(4));
    }
}
