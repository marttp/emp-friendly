package dev.tpcoder.empfriendly.location;

import dev.tpcoder.empfriendly.location.model.RequestForDrive;
import dev.tpcoder.empfriendly.location.model.Tracking;
import dev.tpcoder.empfriendly.location.model.Location;
import dev.tpcoder.empfriendly.location.service.SupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/locations")
@Slf4j
public class LocationController {

  private final SupportService supportService;

  public LocationController(SupportService supportService) {
    this.supportService = supportService;
  }

//  @GetMapping(value = "/{trackingId}/track", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//  public Flux<Location> liveTracking(@PathVariable String trackingId) {
//    log.debug("Request to watching trackingId: {}", trackingId);
//    return supportService.tracking(trackingId);
//  }

  @GetMapping(value = "/{trackingId}/history")
  public Flux<Location> getTrackingHistory(@PathVariable String trackingId) {
    log.debug("Request trackingId: {} history", trackingId);
    return supportService.tracking(trackingId);
  }

  @PostMapping("/mock/drive")
  public Mono<Tracking> createMockJourney(@RequestBody RequestForDrive body) {
    var result = supportService.createTracking(body);
    return Mono.just(result);
  }
}
