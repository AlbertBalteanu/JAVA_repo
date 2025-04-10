package com.example;

import java.util.ArrayList;
import java.util.List;


public class Board {
    private final List<String> submittedWords;

    public Board() {
        submittedWords = new ArrayList<>();
    }

    public synchronized void submitWord(String playerName, String word, int points) {
        submittedWords.add(word);
        System.out.println(playerName + " submitted word: " + word + " for " + points + " points");
    }
}