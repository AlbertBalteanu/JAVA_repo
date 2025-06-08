package lab9.common.factory;

import lab9.common.repository.CityRepositoryInterface;
import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.repository.ContinentRepositoryInterface;

public interface AbstractRepositoryFactory {
    CityRepositoryInterface createCityRepository();
    CountryRepositoryInterface createCountryRepository();
    ContinentRepositoryInterface createContinentRepository();
}
