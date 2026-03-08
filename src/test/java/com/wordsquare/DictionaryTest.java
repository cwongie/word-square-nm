package com.wordsquare;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DictionaryTest {

    @Test
    void shouldReturnTrueForValidWord() {
        Dictionary dictionary = new Dictionary();

        boolean result = dictionary.contains("rose");

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForInvalidWord() {
        Dictionary dictionary = new Dictionary();

        boolean result = dictionary.contains("wxyz");

        assertFalse(result);
    }

    @Test
    void shouldFindWordsOfNthLengthFromLetters() {
        Dictionary dictionary = new Dictionary();
        List<Character> letters = List.of('e','e','e','e','d','d','o','o','n','n','n','s','s','s','r','v');

        List<String> result = dictionary.getWordsOfLength(4, letters);

        assertTrue(result.contains("rose"));
        assertTrue(result.contains("oven"));
        assertTrue(result.contains("send"));
        assertTrue(result.contains("ends"));
    }
}