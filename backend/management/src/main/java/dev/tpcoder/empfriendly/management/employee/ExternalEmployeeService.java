package dev.tpcoder.empfriendly.management.employee;

import dev.tpcoder.empfriendly.management.employee.model.Employee;
import dev.tpcoder.empfriendly.management.employee.model.EmployeeQueryPayload;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ExternalEmployeeService implements EmployeeService {

  private static final String PATH = "/employees";
  private final WebClient employeeWebClient;

  public ExternalEmployeeService(
      @Qualifier("employeeWebClient") WebClient employeeWebClient) {
    this.employeeWebClient = employeeWebClient;
  }

  @Override
  public Flux<Employee> findByAgeBetween(int minAge, int maxAge) {
    String path = createPath("/age");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("min", minAge)
            .queryParam("max", maxAge)
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByAddressLocNear(EmployeeQueryPayload payload) {
    String path = createPath("/loc_near");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("lat", payload.getAddressLoc().y())
            .queryParam("long", payload.getAddressLoc().x())
            .queryParam("d", payload.getSearchRadius())
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByFirstNameAndLastName(EmployeeQueryPayload payload) {
    String path = createPath("/name");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("first", payload.getFirstName())
            .queryParam("last", payload.getLastName())
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByAddress_City(EmployeeQueryPayload payload) {
    String path = createPath("/city");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("city", payload.getCity())
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByAddress_CityAndAddress_State(EmployeeQueryPayload payload) {
    String path = createPath("/city_state");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("city", payload.getCity())
            .queryParam("state", payload.getState())
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByTags(EmployeeQueryPayload payload) {
    String path = createPath("/tags");
    String queryTags = buildTagQueryString(payload.getTags());
    log.debug("queryTags: {}", queryTags);
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("tags", queryTags)
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByTagsContainingAll(EmployeeQueryPayload payload) {
    String path = createPath("/tags/all");
    String queryTags = buildTagQueryString(payload.getTags());
    log.debug("queryTags: {}", queryTags);
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("tags", queryTags)
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> findByType(String employeeType) {
    String path = createPath("/type/{expectType}");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path).build(employeeType))
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Flux<Employee> search(String text) {
    String path = createPath("/search");
    return this.employeeWebClient.get()
        .uri(uriBuilder -> uriBuilder.path(path)
            .queryParam("q", text)
            .build())
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Employee.class);
  }

  @Override
  public Mono<Employee> createEmployee(Employee employee) {
    return this.employeeWebClient.put()
        .uri(PATH)
        .header("requestUid", UUID.randomUUID().toString())
        .bodyValue(employee)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  @Override
  public Mono<Employee> updateEmployee(Employee employee) {
    String id = employee.getId();
    String path = createPath("/{empId}");
    return this.employeeWebClient.put()
        .uri(uriBuilder -> uriBuilder.path(path).build(id))
        .header("requestUid", UUID.randomUUID().toString())
        .bodyValue(employee)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  private String createPath(String suffixPath) {
    return PATH + suffixPath;
  }

  private String buildTagQueryString(Set<String> tags) {
    if (CollectionUtils.isEmpty(tags) || tags.isEmpty()) {
      return "";
    }
    return tags.parallelStream().collect(Collectors.joining(","));
  }
}
