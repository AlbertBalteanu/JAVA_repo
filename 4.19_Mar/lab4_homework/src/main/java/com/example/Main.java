package com.example;

import java.util.*;
import java.util.stream.Collectors;

import com.github.javafaker.*;
import org.graph4j.*;
import org.graph4j.generators.EdgeWeightsGenerator;
import org.graph4j.shortestpath.DijkstraShortestPathDefault;
import org.graph4j.util.Path;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();
        Random random = new Random();
        Location[] locations = {
            new Location(1, faker.address().city(), LocationType.FRIENDLY),
            new Location(2, faker.address().city(), LocationType.ENEMY),
            new Location(3, faker.address().city(), LocationType.NEUTRAL),
            new Location(4, faker.address().city(), LocationType.FRIENDLY),
            new Location(5, faker.address().city(), LocationType.ENEMY),
            new Location(6, faker.address().city(), LocationType.NEUTRAL),
            new Location(7, faker.address().city(), LocationType.FRIENDLY),
            new Location(8, faker.address().city(), LocationType.ENEMY),
            new Location(9, faker.address().city(), LocationType.NEUTRAL),
            new Location(10, faker.address().city(), LocationType.FRIENDLY)
        };

        for (Location location : locations) {
            int traversableCount = Math.max(1, random.nextInt(10)); 
            Set<Location> traversableLocations = new HashSet<>();
            while (traversableLocations.size() < traversableCount) {
                Location randomLocation = locations[random.nextInt(locations.length)]; 
                if (!randomLocation.equals(location)) { 
                    traversableLocations.add(randomLocation);
                }
            }
            Location[] traversableArray = traversableLocations.toArray(new Location[0]);
            location.setTraversableLocations(traversableArray);
        }


        @SuppressWarnings("rawtypes")
        Digraph g = GraphBuilder.vertexRange(1, 10).buildDigraph();
        for (Location location : locations) {
            for (Location traversable : location.getTraversableLocations()) {
                g.addEdge(location.getId(), traversable.getId());
            }
        }

        EdgeWeightsGenerator.randomIntegers(g, 1, 20);

        int target = random.nextInt(8) + 2; 
        var alg = new DijkstraShortestPathDefault(g, 1);
        alg.computePath(target);
        Path path = alg.findPath(target);
        System.out.print("Shortest path from " + locations[0].getName() + " to " + locations[target - 1].getName() + " is: ");
        System.out.print(locations[0].getName());
        for (int i = 1; i < path.vertices().length; i++) {
            System.out.print(" -> " + locations[path.vertices()[i] - 1].getName());
        }
        System.out.print(" at a time of " + alg.getPathWeight(target) + " hours.");
        System.out.println();

        Map<LocationType, List<Location>> locationsByType = Arrays.stream(locations)
            .collect(Collectors.groupingBy(Location::getType));

        locationsByType.forEach((type, locs) -> {
            System.out.println(type + ": " + locs.stream()
            .map(Location::getName)
            .collect(Collectors.joining(", ")));
        });
    }
}