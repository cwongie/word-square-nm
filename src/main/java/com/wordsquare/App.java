package com.wordsquare;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar word-square.jar \"4 eeeeddoonnnsssrv\"");
            return;
        }
        System.out.println(run(args[0]));
    }

    public static String run(String input) {
        // Split input into size and letters
        String[] parts = input.trim().split(" ");
        int size = Integer.parseInt(parts[0]);
        String lettersString = parts[1];

        // Convert letter string into a List of Characters
        List<Character> letters = new ArrayList<>();
        for (char c : lettersString.toCharArray()) {
            letters.add(c);
        }

        // Solve
        Dictionary dictionary = new Dictionary();
        WordSquareSolver solver = new WordSquareSolver(dictionary);
        WordSquare result = solver.solve(size, letters);

        if (result == null) {
            return "No solution found";
        }

        // output string
        StringBuilder output = new StringBuilder();
        List<String> words = result.getWords();
        for (int i = 0; i < words.size(); i++) {
            output.append(words.get(i));
            if (i < words.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}