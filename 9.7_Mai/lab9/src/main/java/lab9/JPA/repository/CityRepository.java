package lab9.JPA.repository;

import lab9.JPA.entity.City;

public class CityRepository extends AbstractRepository<City> {    
    public CityRepository() {
        super(City.class);
    }
}