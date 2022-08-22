package dev.tpcoder.empfriendly.driver.service.impl;

import dev.tpcoder.empfriendly.driver.model.Location;
import dev.tpcoder.empfriendly.driver.service.LocationService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ExternalLocationService implements LocationService {

  private final Logger logger = LoggerFactory.getLogger(ExternalLocationService.class);
  private final WebClient locationWebClient;

  public ExternalLocationService(@Qualifier("locationWebClient") WebClient locationWebClient) {
    this.locationWebClient = locationWebClient;
  }

  @Override
  public Flux<Location> getLocationHistory(String id) {
    logger.debug("Starting Calling get tracking history");
    return this.locationWebClient.get()
        .uri(uriBuilder -> uriBuilder.path("/locations/{id}/history").build(id))
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .bodyToFlux(Location.class);
  }
}
