package dev.tpcoder.empfriendly.driver.controller;

import dev.tpcoder.empfriendly.driver.enumeration.DriveType;
import dev.tpcoder.empfriendly.driver.model.AcceptOrder;
import dev.tpcoder.empfriendly.driver.model.Location;
import dev.tpcoder.empfriendly.driver.service.LocationService;
import dev.tpcoder.empfriendly.driver.service.OrderService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
@RequestMapping("/drivers")
public class DriverController {

  private final Logger logger = LoggerFactory.getLogger(DriverController.class);

  private final LocationService locationService;
  private final OrderService orderService;

  public DriverController(LocationService locationService, OrderService orderService) {
    this.locationService = locationService;
    this.orderService = orderService;
  }

  @GetMapping(value = "/{id}/history")
  public Flux<Location> getLocationHistory(@PathVariable String id) {
    logger.debug("Request trackingId: {} history", id);
    return locationService.getLocationHistory(id);
  }

  @PostMapping(value = "/accept")
  public Mono<Void> acceptOrder(@RequestBody @Valid AcceptOrder body) {
    logger.debug("Accept Order Body: {}", body);
    return body.type() == DriveType.DELIVER ? orderService.acceptDeliverOrder(body.id())
        : orderService.acceptJourneyOrder(body.id());
  }
}
