package lab9.common.repository;
import lab9.common.dto.ContinentDto;
import java.util.List;

public interface ContinentRepositoryInterface {
    ContinentDto create(ContinentDto continent);
    ContinentDto findById(int id);
    List<ContinentDto> findByName(String namePattern);
}
