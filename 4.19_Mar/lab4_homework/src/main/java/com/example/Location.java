package com.example;

class Location implements Comparable<Location> {

    private int id;
    private String name;
    private LocationType type;
    private Location[] traversableLocations;
    public Location(int id, String name, LocationType type, Location[] traversableLocations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.traversableLocations = traversableLocations;
    }

    public Location(int id, String name, LocationType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location[] getTraversableLocations() {
        return traversableLocations;
    }
    
    public void setTraversableLocations(Location[] traversableLocations) {
        this.traversableLocations = traversableLocations;
    }

    public String getName() {
        return name;
    }
    
    public LocationType getType() {
        return type;
    }

    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }
    
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}