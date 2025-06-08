package lab9.common.repository;

import lab9.common.dto.CityDto;
import java.util.List;

public interface CityRepositoryInterface {
    CityDto create(CityDto city);
    CityDto findById(int id);
    List<CityDto> findByName(String namePattern);
}