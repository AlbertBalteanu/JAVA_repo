package com.example;

import java.util.ArrayList;

public class Airport {

    protected static ArrayList<String> airplanes = new ArrayList<>();
    
    Airport() {
    }

    @Override
    public String toString() {
        return "airplanes=" + airplanes;
    }
}