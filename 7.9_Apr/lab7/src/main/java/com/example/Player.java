package com.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Tile> hand;
    private int score;

    public Player(String name, Bag bag, Board board, Dictionary dictionary) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public void run() {
        drawTiles(7);
        
        while (!bag.isEmpty() || !hand.isEmpty()) {
            if (hand.isEmpty() && bag.isEmpty()) {
                break;
            }
            
            String word = formWord();
            
            if (word != null) {
                int wordPoints = calculatePoints(word);
                
                board.submitWord(name, word, wordPoints);
                
                score += wordPoints;
                
                removeTilesForWord(word);
                
                drawTiles(word.length());
            } else if (!bag.isEmpty()) {
                System.out.println(name + " could not form a word, discarding tiles and drawing new ones");
                hand.clear();
                drawTiles(7);
            } else {
                break;
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        System.out.println(name + " finished with a score of " + score);
    }


    private void drawTiles(int count) {
        List<Tile> newTiles = bag.extractTiles(count);
        hand.addAll(newTiles);
        
        if (!newTiles.isEmpty()) {
            System.out.println(name + " drew " + newTiles.size() + " tiles: " + newTiles);
        }
    }


    private String formWord() {
        int[] letterCount = new int[26];
        for (Tile tile : hand) {
            letterCount[tile.getLetter() - 'A']++;
        }
        
        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            if (letterCount[c1 - 'A'] > 0) {
                String word = String.valueOf(c1);
                if (dictionary.contains(word)) {
                    return word;
                }
                
                for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                    if ((c2 == c1 && letterCount[c2 - 'A'] > 1) || (c2 != c1 && letterCount[c2 - 'A'] > 0)) {
                        word = String.valueOf(c1) + c2;
                        if (dictionary.contains(word)) {
                            return word;
                        }
                        
                        for (char c3 = 'A'; c3 <= 'Z'; c3++) {
                            int c3Count = (c3 == c1 ? letterCount[c3 - 'A'] - 1 : 
                                          (c3 == c2 ? letterCount[c3 - 'A'] - 1 : letterCount[c3 - 'A']));
                            
                            if (c3Count > 0) {
                                word = String.valueOf(c1) + c2 + c3;
                                if (dictionary.contains(word)) {
                                    return word;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return null;
    }


    private int calculatePoints(String word) {
        int points = 0;
        for (char c : word.toCharArray()) {
            // Find the first tile with this letter
            for (Tile tile : hand) {
                if (tile.getLetter() == c) {
                    points += tile.getPoints();
                    break;
                }
            }
        }
        return points;
    }


    private void removeTilesForWord(String word) {
        for (char c : word.toCharArray()) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getLetter() == c) {
                    hand.remove(i);
                    break;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}