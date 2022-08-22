package dev.tpcoder.empfriendly.driver.service;

import reactor.core.publisher.Mono;

public interface OrderService {

  Mono<Void> acceptDeliverOrder(String id);

  Mono<Void> acceptJourneyOrder(String id);
}
