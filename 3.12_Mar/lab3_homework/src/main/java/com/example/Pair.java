package com.example;

import java.time.LocalDate;

class Pair{
    
    private LocalDate takeoffTime;
    private LocalDate landingTime;
    public Pair(LocalDate takeoffTime, LocalDate landingTime) {
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
    }
    public LocalDate getTakeoffTime() {
        return takeoffTime;
    }
    public void setTakeoffTime(LocalDate takeoffTime) {
        this.takeoffTime = takeoffTime;
    }
    public LocalDate getLandingTime() {
        return landingTime;
    }
    public void setLandingTime(LocalDate landingTime) {
        this.landingTime = landingTime;
    }

    

}