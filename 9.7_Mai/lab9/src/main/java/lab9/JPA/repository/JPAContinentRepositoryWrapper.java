package lab9.JPA.repository;

import lab9.common.repository.ContinentRepositoryInterface;
import lab9.common.dto.ContinentDto;
import lab9.JPA.entity.Continent;
import java.util.List;
import java.util.ArrayList;

public class JPAContinentRepositoryWrapper implements ContinentRepositoryInterface {
    private final ContinentRepository jpaRepo;
    
    public JPAContinentRepositoryWrapper() {
        this.jpaRepo = new ContinentRepository();
    }
    
    @Override
    public ContinentDto create(ContinentDto continentDto) {
        try {
            Continent jpaContinent = new Continent(continentDto.getName());
            
            Continent savedContinent = jpaRepo.create(jpaContinent);
            return convertToDto(savedContinent);
            
        } catch (Exception e) {
            throw new RuntimeException("JPA create failed", e);
        }
    }
    
    @Override
    public ContinentDto findById(int id) {
        try {
            Continent jpaContinent = jpaRepo.findById(id);
            return convertToDto(jpaContinent);
        } catch (Exception e) {
            throw new RuntimeException("JPA findById failed", e);
        }
    }
    
    @Override
    public List<ContinentDto> findByName(String namePattern) {
        try {
            List<Continent> jpaContinents = jpaRepo.findByName(namePattern);
            List<ContinentDto> dtos = new ArrayList<>();
            
            for (Continent continent : jpaContinents) {
                dtos.add(convertToDto(continent));
            }
            
            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("JPA findByName failed", e);
        }
    }
    
    private ContinentDto convertToDto(Continent jpaContinent) {
        if (jpaContinent == null) return null;
        
        return new ContinentDto(
            jpaContinent.getId(),
            jpaContinent.getName()
        );
    }
}