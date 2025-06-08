package lab9.JPA.repository;


import lab9.JPA.entity.Continent;

public class ContinentRepository extends AbstractRepository<Continent> {
    
    public ContinentRepository() {
        super(Continent.class);
    }
}