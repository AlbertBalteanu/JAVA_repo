package com.example;

public class Freighter extends Airplane implements CargoCapable{

    Freighter(String name) {
        super(name);
        Airport.airplanes.add(this.name);
    }

}
