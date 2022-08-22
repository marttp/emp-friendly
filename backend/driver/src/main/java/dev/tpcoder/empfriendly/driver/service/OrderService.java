package dev.tpcoder.empfriendly.driver.service;

import dev.tpcoder.empfriendly.driver.model.OrderResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

  Mono<Void> acceptDeliverOrder(String id);

  Mono<Void> acceptJourneyOrder(String id);

  Flux<OrderResponse> getActiveOrder(String userId);
}
