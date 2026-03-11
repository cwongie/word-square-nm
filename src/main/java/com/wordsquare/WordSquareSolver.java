package com.wordsquare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordSquareSolver {

    private final Dictionary dictionary;

    public WordSquareSolver(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public WordSquare solve(int size, List<Character> letters) {
        List<String> candidates = dictionary.getWordsOfLength(size, letters);
        WordSquare wordSquare = new WordSquare(size);
        if (search(wordSquare, size, candidates, letters)) {
            return wordSquare;
        }
        return null;
    }

    private boolean search(WordSquare wordSquare, int size, List<String> candidates, List<Character> letters) {
        if (wordSquare.isComplete()) {
            return true;
        }

        int currentRow = wordSquare.getWordCount();
        String prefix = wordSquare.getColumnPrefix(currentRow);

        List<String> filteredCandidates = candidates.stream().filter(word -> dictionary.canMakeWord(word, letters))
                .filter(word -> word.startsWith(prefix))
                .collect(Collectors.toList());
        for (String candidate : filteredCandidates) {
            wordSquare.addWord(candidate);

            List<Character> lettersRemaining = removesLetters(candidate, letters);

            if (columnsStillViable(wordSquare, size, candidates, lettersRemaining)) {
                if (search(wordSquare, size, candidates, lettersRemaining)) {
                    return true;
                }
            }
            wordSquare.removeLastWord();
        }

        return false;
    }

    private boolean columnsStillViable(WordSquare wordSquare, int size, List<String> candidates,
            List<Character> lettersRemaining) {
        int currentRow = wordSquare.getWordCount();
        for (int col = currentRow; col < size; col++) {
            String prefix = wordSquare.getColumnPrefix(col);
            boolean anyMatch = candidates.stream().filter(word -> dictionary.canMakeWord(word, lettersRemaining))
                    .anyMatch(word -> word.startsWith(prefix));
            if (!anyMatch) {
                return false;
            }
        }
        return true;
    }

    public List<Character> removesLetters(String candidate, List<Character> letters) {
        List<Character> lettersRemaining = new ArrayList<>(letters);
        for (char c : candidate.toCharArray()) {
            lettersRemaining.remove(Character.valueOf(c));
        }
        return lettersRemaining;
    }
}