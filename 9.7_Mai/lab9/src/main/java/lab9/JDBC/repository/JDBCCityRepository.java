package lab9.JDBC.repository;

import lab9.common.repository.CityRepositoryInterface;
import lab9.common.dto.CityDto;
import lab9.JDBC.DAOs.CityDAO;
import java.util.List;
import java.util.ArrayList;


public class JDBCCityRepository implements CityRepositoryInterface {
    private static int nextId = 100; 
    
    @Override
    public CityDto create(CityDto cityDto) {
        try {
            if (cityDto.getId() == 0) {
                cityDto.setId(getNextId());
            }
            
            lab9.JDBC.entity.City jdbcCity = new lab9.JDBC.entity.City(
                cityDto.getId(),
                cityDto.getName(), 
                cityDto.getCountryName(),
                cityDto.isCapital(),
                cityDto.getLatitude(),
                cityDto.getLongitude(),
                cityDto.getPopulation()
            );
            
            CityDAO.createCity(jdbcCity);
            
            return cityDto;
            
        } catch (Exception e) {
            throw new RuntimeException("JDBC create failed", e);
        }
    }
    
    @Override
    public CityDto findById(int id) {
        try {
            lab9.JDBC.entity.City jdbcCity = CityDAO.getCityById(id);
            return convertToDto(jdbcCity);
        } catch (Exception e) {
            throw new RuntimeException("JDBC findById failed", e);
        }
    }
    
    @Override
    public List<CityDto> findByName(String namePattern) {
        try {
            // trebuie adaugata metoda in citydao
            List<CityDto> result = new ArrayList<>();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("JDBC findByName failed", e);
        }
    }
    
    private CityDto convertToDto(lab9.JDBC.entity.City jdbcCity) {
        if (jdbcCity == null) return null;
        
        return new CityDto(
            jdbcCity.getId(),
            jdbcCity.getName(),
            jdbcCity.getCountry(), 
            jdbcCity.isCapital(),
            jdbcCity.getLatitude(),
            jdbcCity.getLongitude(),
            jdbcCity.getPopulation()
        );
    }
    
    private synchronized int getNextId() {
        return nextId++;
    }
}