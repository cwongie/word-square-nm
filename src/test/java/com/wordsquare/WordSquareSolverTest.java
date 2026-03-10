package com.wordsquare;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class WordSquareSolverTest {

    @Test
    void shouldSolveA4x4WordSquare() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of(
            'e','e','e','e','d','d','o','o','n','n','n','s','s','s','r','v'
        );

        WordSquareSolver solver = new WordSquareSolver(dictionary);
        WordSquare result = solver.solve(4, letters);

        assertNotNull(result);
        assertTrue(result.isComplete());
    }
}