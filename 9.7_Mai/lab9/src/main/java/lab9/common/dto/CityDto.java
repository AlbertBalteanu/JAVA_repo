package lab9.common.dto;

import java.math.BigDecimal;

public class CityDto {
 private int id;
    private String name;
    private String countryName;    
    private boolean capital;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int population;        
    
    public CityDto() {}
    
    public CityDto(int id, String name, String countryName, boolean capital, 
                   BigDecimal latitude, BigDecimal longitude, int population) {
        this.id = id;
        this.name = name;
        this.countryName = countryName;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }
    
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
    
    public String getCountryName() { 
        return countryName; 
    }
    public void setCountryName(String countryName) { 
        this.countryName = countryName; 
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
    
    public int getPopulation() { 
        return population; 
    }
    public void setPopulation(int population) { 
        this.population = population; 
    }
    
    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                '}';
    }
}
