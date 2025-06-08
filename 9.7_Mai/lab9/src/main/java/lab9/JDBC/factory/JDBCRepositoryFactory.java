package lab9.JDBC.factory;

import lab9.common.factory.AbstractRepositoryFactory;
import lab9.common.repository.CityRepositoryInterface;
import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.repository.ContinentRepositoryInterface;
import lab9.JDBC.repository.JDBCCityRepository;
import lab9.JDBC.repository.JDBCCountryRepository;
import lab9.JDBC.repository.JDBCContinentRepository;


public class JDBCRepositoryFactory implements AbstractRepositoryFactory {
    
    @Override
    public CityRepositoryInterface createCityRepository() {
        return new JDBCCityRepository();
    }
    
    @Override
    public CountryRepositoryInterface createCountryRepository() {
        return new JDBCCountryRepository();
    }
    
    @Override
    public ContinentRepositoryInterface createContinentRepository() {
        return new JDBCContinentRepository();
    }
}