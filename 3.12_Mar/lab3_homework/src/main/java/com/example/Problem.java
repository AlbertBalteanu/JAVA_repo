package com.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {

    private Airport airport;
    private List<Flight> flights;

    public Problem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }

    public Map<String, List<Flight>> mapFlightsToRunways() {
        Map<String, List<Flight>> runwayMap = new HashMap<>();
        for (String runway : airport.getRunways()) {
            runwayMap.put(runway, new ArrayList<>());
        }

        for (Flight flight : flights) {
            for (String runway : airport.getRunways()) {
                if (canAssignFlight(runwayMap.get(runway), flight)) {
                    runwayMap.get(runway).add(flight);
                    break;
                }
            }
        }

        return runwayMap;
    }

    private boolean canAssignFlight(List<Flight> assignedFlights, Flight newFlight) {
        for (Flight flight : assignedFlights) {
            if (isOverlapping(flight, newFlight)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlapping(Flight flight1, Flight flight2) {
        LocalTime start1 = flight1.getTimeIntervalStart();
        LocalTime end1 = flight1.getTimeIntervalEnd();
        LocalTime start2 = flight2.getTimeIntervalStart();
        LocalTime end2 = flight2.getTimeIntervalEnd();

        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Map<String, List<Flight>> runwayMap = mapFlightsToRunways();
        for (Map.Entry<String, List<Flight>> entry : runwayMap.entrySet()) {
            sb.append("Runway: ").append(entry.getKey()).append("\n");
            for (Flight flight : entry.getValue()) {
                sb.append("  Flight: ").append(flight.getFlightId()).append(", ")
                  .append(flight.getTimeIntervalStart()).append(" - ")
                  .append(flight.getTimeIntervalEnd()).append("\n");
            }
        }
        return sb.toString();
    }
}