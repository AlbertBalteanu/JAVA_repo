package com.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Dictionary {
    private final Set<String> words;

    public Dictionary() {
        // Initialize with some sample words
        words = new HashSet<>(Arrays.asList(
            "CAT", "DOG", "BIRD", "FISH", "LION", "TIGER", "BEAR", "WOLF", "FOX", 
            "SNAKE", "EAGLE", "HAWK", "OWL", "DEER", "MOUSE", "RABBIT", "SQUIRREL",
            "FROG", "TOAD", "DUCK", "GOOSE", "SWAN", "HORSE", "COW", "PIG", "SHEEP",
            "GOAT", "CHICKEN", "TURKEY", "DOVE", "PIGEON", "CROW", "RAVEN", "SPARROW",
            "ROBIN", "BLUE", "RED", "GREEN", "YELLOW", "ORANGE", "PURPLE", "BROWN",
            "BLACK", "WHITE", "GRAY", "PINK", "GOLD", "SILVER", "BRONZE", "COPPER"
        ));
    }


    public boolean contains(String word) {
        return words.contains(word.toUpperCase());
    }
}