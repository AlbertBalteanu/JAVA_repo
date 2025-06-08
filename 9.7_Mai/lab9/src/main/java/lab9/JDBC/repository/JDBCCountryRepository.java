package lab9.JDBC.repository;

import lab9.common.repository.CountryRepositoryInterface;
import lab9.common.dto.CountryDto;
import lab9.JDBC.DAOs.CountryDAO;
import java.util.List;
import java.util.ArrayList;

public class JDBCCountryRepository implements CountryRepositoryInterface {
    private static int nextId = 200;
    
    @Override
    public CountryDto create(CountryDto countryDto) {
        try {
            if (countryDto.getId() == 0) {
                countryDto.setId(getNextId());
            }
            
            lab9.JDBC.entity.Country jdbcCountry = new lab9.JDBC.entity.Country(
                countryDto.getName(),
                countryDto.getCode(),
                countryDto.getContinentName()
            );
            jdbcCountry.setId(countryDto.getId());
            
            CountryDAO.createCountry(jdbcCountry);
            return countryDto;
            
        } catch (Exception e) {
            throw new RuntimeException("JDBC create failed", e);
        }
    }
    
    @Override
    public CountryDto findById(int id) {
        try {
            lab9.JDBC.entity.Country jdbcCountry = CountryDAO.getCountryById(id);
            return convertToDto(jdbcCountry);
        } catch (Exception e) {
            throw new RuntimeException("JDBC findById failed", e);
        }
    }
    
    @Override
    public List<CountryDto> findByName(String namePattern) {
        try {
            List<CountryDto> result = new ArrayList<>();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("JDBC findByName failed", e);
        }
    }
    
    private CountryDto convertToDto(lab9.JDBC.entity.Country jdbcCountry) {
        if (jdbcCountry == null) return null;
        
        return new CountryDto(
            jdbcCountry.getId(),
            jdbcCountry.getName(),
            jdbcCountry.getCode(),
            jdbcCountry.getContinent()
        );
    }
    
    private synchronized int getNextId() {
        return nextId++;
    }
}