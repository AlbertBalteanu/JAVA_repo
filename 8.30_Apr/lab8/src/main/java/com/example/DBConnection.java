package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    private DBConnection() {
    }

    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found. Did you add the dependency?");
            e.printStackTrace();
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/world_cities";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL database successfully!");
            } else {
                System.out.println("Failed to connect to the PostgreSQL database.");
            }
        } catch (SQLException e) {
            System.out.println("Connection to PostgreSQL database failed!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/world_cities";
        String user = "postgres";
        String password = "postgres";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Failed to create a connection to the PostgreSQL database.");
            e.printStackTrace();
            return null;
        }
    }
}