package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAO {

    private CityDAO(DBConnection dbConnection) {
    }

    public static int createCity(City city) throws SQLException {
        String sql = "INSERT INTO cities (id, name, country, capital, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, city.getId());
            pstmt.setString(2, city.getName());
            pstmt.setString(3, city.getCountry());
            pstmt.setBoolean(4, city.isCapital());
            pstmt.setBigDecimal(5, city.getLatitude());
            pstmt.setBigDecimal(6, city.getLongitude());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating city failed, no rows affected.");
            }
            
            return city.getId();
        }
    }

    public static City getCityById(int id) throws SQLException {
        String sql = "SELECT id, name, country, capital, latitude, longitude FROM cities WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCity(rs);
                }
            }
        }
        
        return null;
    }

    public static City getCityByName(String name) throws SQLException {
        String sql = "SELECT id, name, country, capital, latitude, longitude FROM cities WHERE name = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCity(rs);
                }
            }
        }
        
        return null;
    }

    private static City mapResultSetToCity(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setName(rs.getString("name"));
        city.setCountry(rs.getString("country"));
        city.setCapital(rs.getBoolean("capital"));
        city.setLatitude(rs.getBigDecimal("latitude"));
        city.setLongitude(rs.getBigDecimal("longitude"));
        System.out.println("City: " + city.getName() + ", Country: " + city.getCountry() + ", Capital: " + city.isCapital() + ", Latitude: " + city.getLatitude() + ", Longitude: " + city.getLongitude());
        return city;
    }
}
