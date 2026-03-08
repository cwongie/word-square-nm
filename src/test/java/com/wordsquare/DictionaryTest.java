package com.wordsquare;

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
}