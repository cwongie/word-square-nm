package com.wordsquare;

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
        if (search(wordSquare, size, candidates)) {
            return wordSquare;
        }
        return null;
    }

    private boolean search(WordSquare wordSquare, int size, List<String> candidates) {
        if (wordSquare.isComplete()) {
            return true;
        }

        int currentRow = wordSquare.getWordCount();
        String prefix = wordSquare.getColumnPrefix(currentRow);

        List<String> filteredCandidates = candidates.stream()
                .filter(word -> word.startsWith(prefix))
                .collect(Collectors.toList());

        for (String candidate : filteredCandidates) {
            wordSquare.addWord(candidate);

            if (columnsStillViable(wordSquare, size, candidates)) {
                if (search(wordSquare, size, candidates)) {
                    return true;
                }
            }
            wordSquare.removeLastWord();
        }

        return false;
    }

    private boolean columnsStillViable(WordSquare wordSquare, int size, List<String> candidates) {
        int currentRow = wordSquare.getWordCount();
        for (int col = currentRow; col < size; col++) {
            String prefix = wordSquare.getColumnPrefix(col);
            boolean anyMatch = candidates.stream()
                    .anyMatch(word -> word.startsWith(prefix));
            if (!anyMatch) {
                return false;
            }
        }
        return true;
    }
}