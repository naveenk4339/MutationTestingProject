package com.example.mutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void addsTwoNumbers() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(-1, calc.add(2, -3));
    }

    @Test
    void subtractsTwoNumbers() {
        assertEquals(-1, calc.subtract(2, 3));
        assertEquals(7, calc.subtract(10, 3));
    }

    @Test
    void multipliesTwoNumbers() {
        assertEquals(12, calc.multiply(3, 4));
        assertEquals(-6, calc.multiply(-2, 3));
    }

    @Test
    void dividesTwoNumbers() {
        assertEquals(3, calc.divide(10, 3));
        assertEquals(-2, calc.divide(-8, 4));
    }

    @Test
    void divideByZeroThrows() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    @Test
    void detectsEvenAndOddNumbers() {
        assertTrue(calc.isEven(4));
        assertTrue(calc.isEven(0));
        assertFalse(calc.isEven(7));
        assertFalse(calc.isEven(-3));
    }

    @Test
    void returnsMaximum() {
        assertEquals(9, calc.max(9, 2));
        assertEquals(9, calc.max(2, 9));
        assertEquals(5, calc.max(5, 5));
    }
}
