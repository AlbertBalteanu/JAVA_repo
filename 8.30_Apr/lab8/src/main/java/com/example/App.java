package com.example;

import java.math.BigDecimal;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        DBConnection.connect();
        City city = new City(3, "Piatra", "Zanzibar", true, new BigDecimal("47.1585"), new BigDecimal("27.6014"));

        CityDAO.createCity(city);
        CityDAO.getCityById(2);
    }
}
