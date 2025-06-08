package lab9.JPA.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
@NamedQueries({
    @NamedQuery(name = "Country.findByName", 
                query = "SELECT c FROM Country c WHERE c.name LIKE :name")
})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private int code;
    
    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;
    
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities = new ArrayList<>();
    
    // Constructors
    public Country() {}
    
    public Country(String name, int code, Continent continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
    }
    
    
    public Continent getContinent() {
        return continent;
    }
    
    public void setContinent(Continent continent) {
        this.continent = continent;
    }
    
    public List<City> getCities() {
        return cities;
    }
    
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void addCity(City city) {
        cities.add(city);
        city.setCountry(this);
    }
    
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", continent='" + (continent != null ? continent.getName() : "null") + '\'' +
                '}';
    }
}