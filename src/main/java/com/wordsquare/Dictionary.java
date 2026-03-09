package com.wordsquare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<String> getWordsOfLength(int size, List<Character> letters) {
        return words.stream()
                .filter(word -> word.length() == size)
                .filter(word -> canMakeWord(word, letters))
                .collect(Collectors.toList());
    }

    public boolean canMakeWord(String word, List<Character> letters) {
        List<Character> remainingLetters = new ArrayList<>(letters); // makes copy of original list to preserve for next word to be checked
        for (char c : word.toCharArray()) {
            if (!remainingLetters.remove(Character.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

}
