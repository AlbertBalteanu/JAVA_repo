package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Bag {
    private final List<Tile> tiles;
    private final Random random = new Random();

    public Bag() {
        tiles = new ArrayList<>();
        initializeTiles();
        shuffleTiles();
    }

    private void initializeTiles() {
        for (char c = 'A'; c <= 'Z'; c++) {
            for (int i = 0; i < 10; i++) {
                int points = random.nextInt(10) + 1;
                tiles.add(new Tile(c, points));
            }
        }
    }

    private void shuffleTiles() {
        Collections.shuffle(tiles);
    }


    public synchronized List<Tile> extractTiles(int count) {
        List<Tile> extractedTiles = new ArrayList<>();
        int tilesToExtract = Math.min(count, tiles.size());
        
        for (int i = 0; i < tilesToExtract; i++) {
            extractedTiles.add(tiles.remove(0));
        }
        
        return extractedTiles;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }

    public synchronized int getTileCount() {
        return tiles.size();
    }
}