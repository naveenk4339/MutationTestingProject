package com.example.mutation;

/** Converts a score (0-100) to a letter grade - great for boundary mutations (>= vs >). */
public class GradeCalculator {

    public char grade(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100: " + score);
        }
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        }
        return 'F';
    }

    public boolean isPassing(int score) {
        return grade(score) != 'F';
    }
}
