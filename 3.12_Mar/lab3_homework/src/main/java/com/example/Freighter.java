package com.example;

public class Freighter extends Airplane{

    Freighter(String name) {
        super(name);
        Airport.airplanes.add(this.name);
    }

}
