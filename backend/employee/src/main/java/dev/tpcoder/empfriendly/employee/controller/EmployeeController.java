package dev.tpcoder.empfriendly.employee.controller;

import dev.tpcoder.empfriendly.employee.model.Employee;
import dev.tpcoder.empfriendly.employee.model.message.NewEmployeeComingEvent;
import dev.tpcoder.empfriendly.employee.repository.EmployeeRepository;
import dev.tpcoder.empfriendly.employee.service.SendNotificationService;
import dev.tpcoder.empfriendly.employee.utility.EmployeeType;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/employees")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

  private final EmployeeRepository employeeRepository;
  private final SendNotificationService sendNotificationService;

  @GetMapping("/age")
  Flux<Employee> findByAgeBetween(@RequestParam("min") int min, @RequestParam("max") int max) {
    var result = employeeRepository.findByAgeBetween(min, max);
    log.debug("findByAgeBetween result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/loc_near")
  Flux<Employee> findByAddress(@RequestParam("lat") double latitude,
      @RequestParam("long") double longitude, @RequestParam("d") double distance) {
    var centerPoint = new Point(longitude, latitude);
    var distanceExpect = new Distance(distance, Metrics.KILOMETERS);
    log.debug("centerPoint: {}", centerPoint);
    log.debug("distanceExpect: {}", distanceExpect);
    var result = employeeRepository.findByAddressLocNear(centerPoint, distanceExpect);
    log.debug("findByAddress result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/name")
  Flux<Employee> byFirstNameAndLastName(@RequestParam("first") String firstName,
      @RequestParam("last") String lastName) {
    log.debug("firstName: {}, lastName: {}", firstName, lastName);
    var result = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    log.debug("byFirstNameAndLastName result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/search")
  Flux<Employee> fullTextSearch(@RequestParam("q") String q) {
    log.debug("Search query: {}", q);
    var result = employeeRepository.search(q);
    log.debug("fullTextSearch: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/city")
  Flux<Employee> findByCity(@RequestParam("city") String city) {
    log.debug("city: {}", city);
    var result = employeeRepository.findByAddress_City(city);
    log.debug("findByCity result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/city_state")
  Flux<Employee> findByCityAndState(@RequestParam("city") String city,
      @RequestParam("state") String state) {
    log.debug("city: {}, state: {}", city, state);
    var result = employeeRepository.findByAddress_CityAndAddress_State(city, state);
    log.debug("findByCityAndState result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/tags")
  Flux<Employee> byAnySkills(@RequestParam("tags") Set<String> skills) {
    log.debug("skills: {}", skills);
    var result = employeeRepository.findByTags(skills);
    log.debug("byAnySkills result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/tags/all")
  Flux<Employee> byAllSkills(@RequestParam("tags") Set<String> skills) {
    var result = employeeRepository.findByTagsContainingAll(skills);
    log.debug("byAllSkills result: {}", result);
    return Flux.fromIterable(result);
  }

  @GetMapping("/type/{expectType}")
  Flux<Employee> findByType(@PathVariable String expectType) {
    EmployeeType type;
    try {
      type = EmployeeType.valueOf(expectType.toUpperCase());
    } catch (IllegalArgumentException e) {
      return Flux.error(new RuntimeException(e));
    }
    log.debug("EmployeeType: {}", type);
    var result = employeeRepository.findByType(type.name());
    log.debug("findByType: {}", type);
    return Flux.fromIterable(result);
  }

  @PostMapping
  Mono<Employee> createNewEmployee(@RequestBody @Valid Employee body) {
    log.debug("createNewEmployee body: {}", body);
    // Create UUID from server
    body.setId(UUID.randomUUID().toString());
    return Mono.fromCallable(() -> {
      var newEmployee = employeeRepository.save(body);
      var message = "Welcome to our company!";
      var notificationEvent = new NewEmployeeComingEvent(newEmployee.getId(), "new-joiner-welcome", newEmployee.getEmail(), message);
      sendNotificationService.publish(notificationEvent);
      return newEmployee;
    });
  }

  @PutMapping("/{empId}")
  Mono<ResponseEntity<Employee>> createNewEmployee(@PathVariable String empId, @RequestBody @Valid Employee body) {
    var optional = employeeRepository.findById(empId);
    if (optional.isEmpty()) {
      return Mono.just(ResponseEntity.notFound().build());
    }
    var existEmployee = optional.get();
    existEmployee.setFirstName(body.getFirstName());
    existEmployee.setLastName(body.getLastName());
    existEmployee.setAge(body.getAge());
    existEmployee.setAddress(body.getAddress());
    existEmployee.setAddressLoc(body.getAddressLoc());
    log.debug("updatedEmployee: {}", existEmployee);
    return Mono.just(ResponseEntity.ok(employeeRepository.save(existEmployee)));
  }

}
