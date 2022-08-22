package dev.tpcoder.empfriendly.management.employee;

import dev.tpcoder.empfriendly.management.employee.model.Employee;
import dev.tpcoder.empfriendly.management.employee.model.EmployeeQueryPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

  Flux<Employee> findByAgeBetween(int minAge, int maxAge);

  Flux<Employee> findByAddressLocNear(EmployeeQueryPayload payload);

  Flux<Employee> findByFirstNameAndLastName(EmployeeQueryPayload payload);

  Flux<Employee> findByAddress_City(EmployeeQueryPayload payload);

  Flux<Employee> findByAddress_CityAndAddress_State(EmployeeQueryPayload payload);

  Flux<Employee> findByTags(EmployeeQueryPayload payload);

  Flux<Employee> findByTagsContainingAll(EmployeeQueryPayload payload);

  Flux<Employee> findByType(String employeeType);

  Flux<Employee> search(String text);

  Mono<Employee> createEmployee(Employee employee);

  Mono<Employee> updateEmployee(Employee employee);
}
