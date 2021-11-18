package demo.repository;

import java.util.List;
import demo.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  List<Employee> findByCompanyId(Integer companyId);
}
