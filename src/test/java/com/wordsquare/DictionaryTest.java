package com.wordsquare;                        

import org.junit.jupiter.api.Test;              
import static org.junit.jupiter.api.Assertions.*; 

class DictionaryTest {                         

    @Test                                    
    void shouldReturnTrueForValidWord() {      
        Dictionary dictionary = new Dictionary(); 

        boolean result = dictionary.contains("rose");

        assertTrue(result);                   
    }
}