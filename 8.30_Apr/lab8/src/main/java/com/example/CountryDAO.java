package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountryDAO {

    private CountryDAO(DBConnection dbConnection) {
    }
    
    public int createCountry(Country country) throws SQLException {
        String sql = "INSERT INTO countries (id, name, code, continent) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, country.getId());
            pstmt.setString(2, country.getName());
            pstmt.setInt(3, country.getCode());
            pstmt.setString(4, country.getContinent());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating country failed, no rows affected.");
            }
            
            return country.getId();
            }
    }

    public Country getCountryById(int id) throws SQLException {
        String sql = "SELECT id, name, code, continent FROM countries WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCountry(rs);
                }
            }
        }
        
        return null;
    }

    public Country getCountryByName(String name) throws SQLException {
        String sql = "SELECT id, name, code, continent FROM countries WHERE name = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCountry(rs);
                }
            }
        }
        
        return null;
    }

    private Country mapResultSetToCountry(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt("id"));
        country.setName(rs.getString("name"));
        country.setCode(rs.getInt("code"));
        country.setContinent(rs.getString("continent"));
        return country;
    }

}
