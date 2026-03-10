package com.wordsquare;

import java.util.ArrayList;
import java.util.List;

public class WordSquare {

    private final int size;
    private final List<String> words;

    public WordSquare(int size) {
        this.size = size;
        this.words = new ArrayList<>();
    }

    public void addWord(String word) {
        words.add(word);
    }

    public boolean isComplete() {
        return words.size() == size;
    }

    public String getColumnPrefix(int column) {
        StringBuilder prefix = new StringBuilder();
        for (String word : words) {
            prefix.append(word.charAt(column));
        }
        return prefix.toString();
    }

    public int getSize() {
        return size;
    }

    public List<String> getWords() {
        return words;
    }

    public int getWordCount() {
        return words.size();
    }

    public void removeLastWord() {
        if (!words.isEmpty()) {
            words.remove(words.size() - 1);
        }
    }
}