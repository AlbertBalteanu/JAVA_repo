package lab9.JPA.repository;

import lab9.common.repository.CityRepositoryInterface;
import lab9.common.dto.CityDto;
import lab9.JPA.entity.City;
import lab9.JPA.entity.Country;
import java.util.List;
import java.util.ArrayList;


public class JPACityRepositoryWrapper implements CityRepositoryInterface {
    private final CityRepository jpaRepo;
    private final CountryRepository countryRepo;
    
    public JPACityRepositoryWrapper() {
        this.jpaRepo = new CityRepository();
        this.countryRepo = new CountryRepository();
    }
    
    @Override
    public CityDto create(CityDto cityDto) {
        try {
            // Find the country by name
            List<Country> countries = countryRepo.findByName(cityDto.getCountryName());
            Country country = countries.isEmpty() ? null : countries.get(0);
            
            // Convert DTO to JPA entity
            City jpaCity = new City(
                cityDto.getName(),
                country,
                cityDto.isCapital(),
                cityDto.getLatitude(),
                cityDto.getLongitude(),
                cityDto.getPopulation()
            );
            jpaCity.setPopulation(cityDto.getPopulation());
            
            // Use your existing JPA repository
            City savedCity = jpaRepo.create(jpaCity);
            
            // Convert back to DTO
            return convertToDto(savedCity);
            
        } catch (Exception e) {
            throw new RuntimeException("JPA create failed", e);
        }
    }
    
    @Override
    public CityDto findById(int id) {
        try {
            City jpaCity = jpaRepo.findById(id);
            return convertToDto(jpaCity);
        } catch (Exception e) {
            throw new RuntimeException("JPA findById failed", e);
        }
    }
    
    @Override
    public List<CityDto> findByName(String namePattern) {
        try {
            List<City> jpaCities = jpaRepo.findByName(namePattern);
            List<CityDto> dtos = new ArrayList<>();
            
            for (City city : jpaCities) {
                dtos.add(convertToDto(city));
            }
            
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("JPA findByName failed", e);
        }
    }
    
    private CityDto convertToDto(City jpaCity) {
        if (jpaCity == null) return null;
        
        String countryName = jpaCity.getCountry() != null ? 
            jpaCity.getCountry().getName() : null;
        
        return new CityDto(
            jpaCity.getId(),
            jpaCity.getName(),
            countryName,
            jpaCity.isCapital(),
            jpaCity.getLatitude(),
            jpaCity.getLongitude(),
            jpaCity.getPopulation()
        );
    }
}