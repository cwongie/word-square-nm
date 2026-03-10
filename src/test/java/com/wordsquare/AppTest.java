package com.wordsquare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void shouldParseInputAndReturnSolution() {
        
        String input = "4 eeeeddoonnnsssrv";

        String result = App.run(input);

        assertNotNull(result);
        assertEquals(4, result.lines().count());
    }
}