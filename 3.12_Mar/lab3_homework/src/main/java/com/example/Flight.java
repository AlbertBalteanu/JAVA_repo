package com.example;

import java.time.LocalTime;

public class Flight {

    private int flightId;
    private LocalTime timeIntervalStart;
    private LocalTime timeIntervalEnd;
    private Airplane airplane;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public LocalTime getTimeIntervalStart() {
        return timeIntervalStart;
    }

    public void setTimeIntervalStart(LocalTime timeIntervalStart) {
        this.timeIntervalStart = timeIntervalStart;
    }

    public LocalTime getTimeIntervalEnd() {
        return timeIntervalEnd;
    }

    public void setTimeIntervalEnd(LocalTime timeIntervalEnd) {
        this.timeIntervalEnd = timeIntervalEnd;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Flight(int flightId, Airplane airplane, LocalTime timeIntervalStart, LocalTime timeIntervalEnd) {
        this.flightId = flightId;
        this.airplane = airplane;
        this.timeIntervalStart = timeIntervalStart;
        this.timeIntervalEnd = timeIntervalEnd;
    }
}
