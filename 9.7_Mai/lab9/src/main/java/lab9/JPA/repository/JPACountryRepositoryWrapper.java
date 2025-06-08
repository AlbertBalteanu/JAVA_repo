package lab9.JPA.repository;

import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.dto.CountryDto;
import lab9.JPA.entity.Country;
import lab9.JPA.entity.Continent;
import java.util.List;
import java.util.ArrayList;

public class JPACountryRepositoryWrapper implements CountryRepositoryInterface {
    private final CountryRepository jpaRepo;
    private final ContinentRepository continentRepo;
    
    public JPACountryRepositoryWrapper() {
        this.jpaRepo = new CountryRepository();
        this.continentRepo = new ContinentRepository();
    }
    
    @Override
    public CountryDto create(CountryDto countryDto) {
        try {
            List<Continent> continents = continentRepo.findByName(countryDto.getContinentName());
            Continent continent = continents.isEmpty() ? null : continents.get(0);
            
            Country jpaCountry = new Country(
                countryDto.getName(),
                countryDto.getCode(),
                continent
            );
            
            Country savedCountry = jpaRepo.create(jpaCountry);
            return convertToDto(savedCountry);
            
        } catch (Exception e) {
            throw new RuntimeException("JPA create failed", e);
        }
    }
    
    @Override
    public CountryDto findById(int id) {
        try {
            Country jpaCountry = jpaRepo.findById(id);
            return convertToDto(jpaCountry);
        } catch (Exception e) {
            throw new RuntimeException("JPA findById failed", e);
        }
    }
    
    @Override
    public List<CountryDto> findByName(String namePattern) {
        try {
            List<Country> jpaCountries = jpaRepo.findByName(namePattern);
            List<CountryDto> dtos = new ArrayList<>();
            
            for (Country country : jpaCountries) {
                dtos.add(convertToDto(country));
            }
            
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("JPA findByName failed", e);
        }
    }
    
    private CountryDto convertToDto(Country jpaCountry) {
        if (jpaCountry == null) return null;
        
        String continentName = jpaCountry.getContinent() != null ? 
            jpaCountry.getContinent().getName() : null;
        
        return new CountryDto(
            jpaCountry.getId(),
            jpaCountry.getName(),
            jpaCountry.getCode(),
            continentName
        );
    }
}