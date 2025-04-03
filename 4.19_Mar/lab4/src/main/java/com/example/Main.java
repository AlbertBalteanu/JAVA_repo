package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        Location[] locations = {
            new Location("1", LocationType.FRIENDLY),
            new Location("2", LocationType.FRIENDLY),
            new Location("3", LocationType.NEUTRAL),
            new Location("4", LocationType.ENEMY),
            new Location("5", LocationType.ENEMY),
            new Location("6", LocationType.NEUTRAL)
        };
        


        Set<Location> friendlyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));
        
        System.out.println("Friendly locations, natural order:");
        friendlyLocations.forEach(System.out::println);
        

        List<Location> enemyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));
        
        System.out.println("\nEnemy locations, name order:");
        enemyLocations.forEach(System.out::println);
    }
}