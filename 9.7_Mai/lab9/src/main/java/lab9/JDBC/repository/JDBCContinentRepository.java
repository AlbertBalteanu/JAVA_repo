package lab9.JDBC.repository;

import lab9.common.repository.ContinentRepositoryInterface;
import lab9.common.dto.ContinentDto;
import lab9.JDBC.DAOs.ContinentDAO;
import java.util.List;
import java.util.ArrayList;

public class JDBCContinentRepository implements ContinentRepositoryInterface {
    private static int nextId = 300;
    
    @Override
    public ContinentDto create(ContinentDto continentDto) {
        try {
            if (continentDto.getId() == 0) {
                continentDto.setId(getNextId());
            }
            
            lab9.JDBC.entity.Continent jdbcContinent = new lab9.JDBC.entity.Continent(continentDto.getName());
            jdbcContinent.setId(continentDto.getId());
            
            ContinentDAO.createContinent(jdbcContinent);
            return continentDto;
            
        } catch (Exception e) {
            throw new RuntimeException("JDBC create failed", e);
        }
    }
    
    @Override
    public ContinentDto findById(int id) {
        try {
            lab9.JDBC.entity.Continent jdbcContinent = ContinentDAO.getContinentById(id);
            return convertToDto(jdbcContinent);
        } catch (Exception e) {
            throw new RuntimeException("JDBC findById failed", e);
        }
    }
    
    @Override
    public List<ContinentDto> findByName(String namePattern) {
        try {
            List<ContinentDto> result = new ArrayList<>();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("JDBC findByName failed", e);
        }
    }
    
    private ContinentDto convertToDto(lab9.JDBC.entity.Continent jdbcContinent) {
        if (jdbcContinent == null) return null;
        
        return new ContinentDto(
            jdbcContinent.getId(),
            jdbcContinent.getName()
        );
    }
    
    private synchronized int getNextId() {
        return nextId++;
    }
}