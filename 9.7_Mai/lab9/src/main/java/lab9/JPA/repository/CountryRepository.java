package lab9.JPA.repository;

import lab9.JPA.entity.Country;

public class CountryRepository extends AbstractRepository<Country> {
    
    public CountryRepository() {
        super(Country.class);
    }
}