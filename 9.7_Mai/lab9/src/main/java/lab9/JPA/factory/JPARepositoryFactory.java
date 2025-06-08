package lab9.JPA.factory;

import lab9.common.factory.AbstractRepositoryFactory;
import lab9.common.repository.CityRepositoryInterface;
import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.repository.ContinentRepositoryInterface;
import lab9.JPA.repository.JPACityRepositoryWrapper;
import lab9.JPA.repository.JPACountryRepositoryWrapper;
import lab9.JPA.repository.JPAContinentRepositoryWrapper;


public class JPARepositoryFactory implements AbstractRepositoryFactory {
    
    @Override
    public CityRepositoryInterface createCityRepository() {
        return new JPACityRepositoryWrapper();
    }
    
    @Override
    public CountryRepositoryInterface createCountryRepository() {
        return new JPACountryRepositoryWrapper();
    }
    
    @Override
    public ContinentRepositoryInterface createContinentRepository() {
        return new JPAContinentRepositoryWrapper();
    }
}