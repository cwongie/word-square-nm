package com.wordsquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class WordSquareTest {

    @Test
    void shouldNotBeCompleteWhenEmpty() {

        WordSquare wordSquare = new WordSquare(4);

        assertFalse(wordSquare.isComplete());
    }

    @Test
    void shouldBeCompleteWhenAllRowsFilled() {

        WordSquare wordSquare = new WordSquare(4);

        wordSquare.addWord("rose");
        wordSquare.addWord("oven");
        wordSquare.addWord("send");
        wordSquare.addWord("ends");

        assertTrue(wordSquare.isComplete());
    }

    @Test
    void shouldReturnColumnPrefixForNextRow() {
        WordSquare wordSquare = new WordSquare(4);

        wordSquare.addWord("rose");

        assertEquals("r", wordSquare.getColumnPrefix(0));
        assertEquals("o", wordSquare.getColumnPrefix(1));
        assertEquals("s", wordSquare.getColumnPrefix(2));
        assertEquals("e", wordSquare.getColumnPrefix(3));
    }

}