package com.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Player> players;
    private final List<Thread> playerThreads;

    public Game() {
        bag = new Bag();
        board = new Board();
        dictionary = new Dictionary();
        players = new ArrayList<>();
        playerThreads = new ArrayList<>();
    }


    public void addPlayer(String name) {
        Player player = new Player(name, bag, board, dictionary);
        players.add(player);
        playerThreads.add(new Thread(player, name));
    }


    public void start() {
        System.out.println("Starting the game with " + players.size() + " players");
        System.out.println("Bag contains " + bag.getTileCount() + " tiles");
        
        for (Thread thread : playerThreads) {
            thread.start();
        }
        
        for (Thread thread : playerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Game interrupted");
                return;
            }
        }
        
        System.out.println("\nGame Over!");
        Player winner = null;
        int highestScore = -1;
        
        System.out.println("Final Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " points");
            
            if (player.getScore() > highestScore) {
                highestScore = player.getScore();
                winner = player;
            }
        }
        
        if (winner != null) {
            System.out.println("\nThe winner is " + winner.getName() + " with " + winner.getScore() + " points!");
        } else {
            System.out.println("\nThere is no winner.");
        }
    }
}