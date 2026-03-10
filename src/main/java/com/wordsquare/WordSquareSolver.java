package com.wordsquare;

import java.util.List;
import java.util.stream.Collectors;

public class WordSquareSolver {

    private final Dictionary dictionary;

    public WordSquareSolver(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public WordSquare solve(int size, List<Character> letters) {
        WordSquare wordSquare = new WordSquare(size);
        if (search(wordSquare, size, letters)) {
            return wordSquare;
        }
        return null;
    }

    private boolean search(WordSquare wordSquare, int size, List<Character> letters) {
        if (wordSquare.isComplete()) {
            return true;
        }

        int currentRow = wordSquare.getWordCount();
        String prefix = wordSquare.getColumnPrefix(currentRow);

        List<String> candidates = dictionary.getWordsOfLength(size, letters)
                .stream()
                .filter(word -> word.startsWith(prefix))
                .collect(Collectors.toList());

        for (String candidate : candidates) {
            wordSquare.addWord(candidate);
            List<Character> remainingLetters = getRemainingLetters(letters, candidate);

            if (columnsStillViable(wordSquare, size, remainingLetters)) {
                if (search(wordSquare, size, remainingLetters)) {
                    return true;
                }
            }
            wordSquare.removeLastWord();
        }

        return false;
    }

    private boolean columnsStillViable(WordSquare wordSquare, int size, List<Character> remainingLetters) {
        int currentRow = wordSquare.getWordCount();
        for (int col = 0; col < size; col++) {
            String prefix = wordSquare.getColumnPrefix(col);
            boolean anyMatch = dictionary.getWordsOfLength(size, remainingLetters)
                    .stream()
                    .anyMatch(word -> word.startsWith(prefix));
            if (col >= currentRow && !anyMatch) {
                return false;
            }
        }
        return true;
    }

    private List<Character> getRemainingLetters(List<Character> letters, String usedWord) {
        List<Character> remaining = new java.util.ArrayList<>(letters);
        for (char c : usedWord.toCharArray()) {
            remaining.remove(Character.valueOf(c));
        }
        return remaining;
    }
}