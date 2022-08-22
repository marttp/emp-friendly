package dev.tpcoder.empfriendly.management.employee;

import dev.tpcoder.empfriendly.management.employee.model.Address;
import dev.tpcoder.empfriendly.management.employee.model.Employee;
import dev.tpcoder.empfriendly.management.employee.model.EmployeeQueryPayload;
import dev.tpcoder.empfriendly.management.employee.model.Point;
import java.util.Set;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/age")
  Flux<Employee> findByAgeBetween(@RequestParam("min") int min, @RequestParam("max") int max) {
    return employeeService.findByAgeBetween(min, max);
  }

  @GetMapping("/loc_near")
  Flux<Employee> findByAddressLocNear(@RequestParam("lat") double latitude,
      @RequestParam("long") double longitude, @RequestParam("d") double distance) {
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setAddressLoc(new Point(longitude, latitude));
    payload.setSearchRadius(distance);
    log.debug("payload for findByAddressLocNear: {}", payload);
    return employeeService.findByAddressLocNear(payload);
  }

  @GetMapping("/name")
  Flux<Employee> byFirstNameAndLastName(@RequestParam("first") String firstName,
      @RequestParam("last") String lastName) {
    log.debug("firstName: {}, lastName: {}", firstName, lastName);
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setFirstName(firstName);
    payload.setLastName(lastName);
    return employeeService.findByFirstNameAndLastName(payload);
  }

  @GetMapping("/search")
  Flux<Employee> fullTextSearch(@RequestParam("q") String q) {
    log.debug("Search query: {}", q);
    return employeeService.search(q);
  }

  @GetMapping("/city")
  Flux<Employee> findByCity(@RequestParam("city") String city) {
    log.debug("city: {}", city);
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setCity(city);
    return employeeService.findByAddress_City(payload);
  }

  @GetMapping("/city_state")
  Flux<Employee> findByCityAndState(@RequestParam("city") String city,
      @RequestParam("state") String state) {
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setCity(city);
    payload.setState(state);
    return employeeService.findByAddress_CityAndAddress_State(payload);
  }

  @GetMapping("/tags")
  Flux<Employee> byAnySkills(@RequestParam("tags") Set<String> skills) {
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setTags(skills);
    return employeeService.findByTags(payload);
  }

  @GetMapping("/tags/all")
  Flux<Employee> byAllSkills(@RequestParam("tags") Set<String> skills) {
    EmployeeQueryPayload payload = new EmployeeQueryPayload();
    payload.setTags(skills);
    return employeeService.findByTagsContainingAll(payload);
  }

  @GetMapping("/type/{type}")
  Flux<Employee> findByType(@PathVariable String type) {
    log.debug("EmployeeType: {}", type);
    return employeeService.findByType(type);
  }

  @PostMapping
  Mono<Employee> createNewEmployee(@RequestBody @Valid Employee body) {
    log.debug("createNewEmployee body: {}", body);
    return employeeService.createEmployee(body);
  }

  @PutMapping("/{empId}")
  Mono<Employee> updateEmployee(@PathVariable String empId, @RequestBody @Valid Employee body) {
    body.setId(empId);
    log.debug("updateEmployee body: {}", body);
    return employeeService.updateEmployee(body);
  }
}
