package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContinentDAO {

    private ContinentDAO(DBConnection dbConnection) {
        }
        
    public int createContinent(Continent continent) throws SQLException {
        String sql = "INSERT INTO continents (id, name) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, continent.getId());
            pstmt.setString(2, continent.getName());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating continent failed, no rows affected.");
            }
            
            return continent.getId();
        }
    }

    public Continent getContinentById(int id) throws SQLException {
        String sql = "SELECT id, name FROM continents WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToContinent(rs);
                }
            }
        }
        
        return null;
    }

    public Continent getContinentByName(String name) throws SQLException {
        String sql = "SELECT id, name FROM continents WHERE name = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToContinent(rs);
                }
            }
        }
        
        return null;
    }
    
    private Continent mapResultSetToContinent(ResultSet rs) throws SQLException {

        Continent continent = new Continent();
        continent.setId(rs.getInt("id"));
        continent.setName(rs.getString("name"));
        return continent;
    }

}