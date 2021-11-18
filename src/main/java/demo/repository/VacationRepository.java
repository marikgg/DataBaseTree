package demo.repository;

import java.util.List;
import demo.entity.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {
  List<Vacation> findByEmployeeId(Long employeeId);
}
