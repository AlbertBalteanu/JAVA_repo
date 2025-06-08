package com.example;

import java.math.BigDecimal;

public class City {
    private int id;
    private String name;
    private String country;
    private boolean capital;
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    // Constructors
    public City() {}
    
    public City(int id, String name, String country, boolean capital, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public boolean isCapital() {
        return capital;
    }
    
    public void setCapital(boolean capital) {
        this.capital = capital;
    }
    
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
