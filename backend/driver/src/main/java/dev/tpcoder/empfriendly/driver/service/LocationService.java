package dev.tpcoder.empfriendly.driver.service;

import dev.tpcoder.empfriendly.driver.model.Location;
import reactor.core.publisher.Flux;

public interface LocationService {

  Flux<Location> getLocationHistory(String id);
}
