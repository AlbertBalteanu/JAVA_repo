package com.example;


public class Main {
    public static void main(String[] args) {
        // Create a new game
        Game game = new Game();
        
        // Add players
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");
        
        // Start the game
        game.start();
    }
}