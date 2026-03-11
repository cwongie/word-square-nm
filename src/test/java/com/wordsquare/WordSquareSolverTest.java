package com.wordsquare;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class WordSquareSolverTest {

    @Test
    void shouldSolveA4x4WordSquare() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of(
                'a', 'a',
                'c', 'c',
                'd',
                'e', 'e', 'e', 'e',
                'm', 'm',
                'n', 'n', 'n',
                'o', 'o');

        WordSquareSolver solver = new WordSquareSolver(dictionary);
        WordSquare result = solver.solve(4, letters);

        assertNotNull(result);
        assertTrue(result.isComplete());
    }

    @Test
    void shouldSolveA5x5WordSquare() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of(
                'a', 'a', 'a',
                'e', 'e', 'e', 'e',
                'f',
                'h', 'h',
                'm',
                'o', 'o',
                'n',
                's', 's',
                'r', 'r', 'r', 'r',
                't', 't', 't', 't',
                'w');

        WordSquareSolver solver = new WordSquareSolver(dictionary);
        WordSquare result = solver.solve(5, letters);

        assertNotNull(result);
        assertTrue(result.isComplete());
    }

    @Test
    void shouldSolveA7x7WordSquare() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of(
                'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                'b', 'b',
                'e', 'e', 'e', 'e', 'e', 'e', 'e',
                'd', 'd', 'd', 'd', 'd',
                'g', 'g',
                'm', 'm',
                'l', 'l',
                'o', 'o', 'o', 'o',
                'n', 'n',
                's', 's', 's', 's',
                'r', 'r', 'r', 'r',
                'u',
                'v', 'v',
                'y', 'y', 'y');

        WordSquareSolver solver = new WordSquareSolver(dictionary);
        WordSquare result = solver.solve(7, letters);

        assertNotNull(result);
        assertTrue(result.isComplete());
    }

    @Test
    void shouldRemoveCandidateFromLetters() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of(
                'a', 'a',
                'c', 'c',
                'd',
                'e', 'e', 'e', 'e',
                'm', 'm',
                'n', 'n', 'n',
                'o', 'o');

        WordSquareSolver solver = new WordSquareSolver(dictionary);
        boolean result = solver.removesLetters(4, letters);
        assertTrue(result);
    }
}