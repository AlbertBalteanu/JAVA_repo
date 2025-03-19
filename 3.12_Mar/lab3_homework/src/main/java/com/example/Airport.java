package com.example;

import java.util.ArrayList;

public class Airport {

    protected static ArrayList<String> airplanes = new ArrayList<>();
    private ArrayList<String> runways;

    public Airport(ArrayList<String> runways) {
        this.runways = runways;
    }

    public ArrayList<String> getRunways() {
        return runways;
    }

    public void setRunways(ArrayList<String> runways) {
        this.runways = runways;
    }

    Airport() {
    }

    @Override
    public String toString() {
        return "Airport [runways=" + runways + "]";
    }

    
}