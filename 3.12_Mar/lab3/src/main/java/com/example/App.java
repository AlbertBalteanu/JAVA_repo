package com.example;


public class App {
    public static void main(String[] args) {

        Airport A =  new Airport();

        Freighter f = new Freighter("Freighter 1");
        Freighter f2 = new Freighter("Freighter 12");
        Freighter f3 = new Freighter("Freighter 1231435246");
        Airliner a = new Airliner("Airliner 1");
        Drone d = new Drone("Drone 1");

        System.out.println(A.toString());

    }
}
