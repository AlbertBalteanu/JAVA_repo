package lab9.JPA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "continents")
@NamedQueries({
    @NamedQuery(name = "Continent.findByName", 
                query = "SELECT c FROM Continent c WHERE c.name LIKE :name")
})
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    public Continent() {}
    
    public Continent(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}