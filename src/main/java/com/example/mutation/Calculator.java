package com.example.mutation;

/** Simple arithmetic operations - a classic target for math/negation mutators. */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public int max(int a, int b) {
        return a >= b ? a : b;
    }
}
