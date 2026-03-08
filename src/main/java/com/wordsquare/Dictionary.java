package com.wordsquare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    private final Set<String> words;

    public Dictionary() {
        words = new HashSet<>();
        loadWords();
    }

    private void loadWords() {
        try (InputStream input = getClass().getResourceAsStream("/enable1.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not load dictionary", e);
        }
    }

    public boolean contains(String word) {
        return words.contains(word.toLowerCase());
    }
}
