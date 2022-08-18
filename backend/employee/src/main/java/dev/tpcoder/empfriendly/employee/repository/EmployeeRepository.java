package dev.tpcoder.empfriendly.employee.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.employee.model.Employee;
import java.util.Set;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends RedisDocumentRepository<Employee, String> {

  Iterable<Employee> findByAgeBetween(int minAge, int maxAge);

  Iterable<Employee> findByAddressLocNear(Point point, Distance distance);

  Iterable<Employee> findByFirstNameAndLastName(String firstName, String lastName);

  Iterable<Employee> findByAddress_City(String city);

  Iterable<Employee> findByAddress_CityAndAddress_State(String city, String state);

  Iterable<Employee> findByTags(Set<String> skills);

  Iterable<Employee> findByTagsContainingAll(Set<String> skills);

  Iterable<Employee> findByType(String employeeType);

  Iterable<Employee> search(String text);
}
