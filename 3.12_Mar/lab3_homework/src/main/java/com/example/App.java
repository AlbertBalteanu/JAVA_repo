package com.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Airport A = new Airport();

        A.setRunways(new ArrayList<>(Arrays.asList("runway 1", "runway 2", "runway 3")));
        
        Freighter f = new Freighter("Freighter 1");
        Freighter f2 = new Freighter("Freighter 12");
        Freighter f3 = new Freighter("Freighter 1231435246");
        Airliner a = new Airliner("Airliner 1");
        Drone d = new Drone("Drone 1");

        Flight Flight1 = new Flight(1, f, LocalTime.of(12, 30), LocalTime.of(12, 50));
        Flight Flight2 = new Flight(2, f2, LocalTime.of(12, 30), LocalTime.of(12, 50));
        Flight Flight3 = new Flight(3, f3, LocalTime.of(12, 30), LocalTime.of(12, 50));
        Flight Flight4 = new Flight(4, a, LocalTime.of(13, 30), LocalTime.of(13, 50));
        Flight Flight5 = new Flight(5, d, LocalTime.of(13, 30), LocalTime.of(13, 50));

        List<Flight> flights = Arrays.asList(Flight1, Flight2, Flight3, Flight4, Flight5);

        Problem problem = new Problem(A, flights);
        System.out.println(problem.toString());
    }
}