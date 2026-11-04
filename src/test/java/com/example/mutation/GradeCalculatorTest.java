package com.example.mutation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator grades = new GradeCalculator();

    // Testing both sides of every boundary kills "conditional boundary" mutants (>= -> >).
    @ParameterizedTest(name = "score {0} -> {1}")
    @CsvSource({
            "100, A", "90, A",
            "89, B", "80, B",
            "79, C", "70, C",
            "69, D", "60, D",
            "59, F", "0, F"
    })
    void mapsScoreToGrade(int score, char expected) {
        assertEquals(expected, grades.grade(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    void rejectsOutOfRangeScores(int score) {
        assertThrows(IllegalArgumentException.class, () -> grades.grade(score));
    }

    @Test
    void passingIsDOrBetter() {
        assertTrue(grades.isPassing(60));
        assertFalse(grades.isPassing(59));
    }
}
