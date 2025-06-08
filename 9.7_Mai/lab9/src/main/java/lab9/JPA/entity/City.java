package lab9.JPA.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cities")
@NamedQueries({
    @NamedQuery(name = "City.findByName", 
                query = "SELECT c FROM City c WHERE c.name LIKE :name")
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    
    @Column
    private boolean capital;
    
    @Column
    private BigDecimal latitude;
    
    @Column
    private BigDecimal longitude;

    @Column
    private int population;
    
    // Constructors
    public City() {}
    
    public City(String name, Country country, boolean capital, BigDecimal latitude, BigDecimal longitude, int population) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }
       
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
                ", country='" + (country != null ? country.getName() : "null") + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                '}';
    }

}