package lab9.common.repository;

import lab9.common.dto.CountryDto;
import java.util.List;

public interface CountryRepositoryInterface {
    CountryDto create(CountryDto country);
    CountryDto findById(int id);

    List<CountryDto> findByName(String namePattern);
}