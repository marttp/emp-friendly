package dev.tpcoder.empfriendly.driver.service.impl;

import dev.tpcoder.empfriendly.driver.service.OrderService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalOrderService implements OrderService {

  private final Logger logger = LoggerFactory.getLogger(ExternalOrderService.class);
  private final WebClient orderWebClient;

  public ExternalOrderService(@Qualifier("orderWebClient") WebClient orderWebClient) {
    this.orderWebClient = orderWebClient;
  }

  @Override
  public Mono<Void> acceptDeliverOrder(String id) {
    String path = "/orders/deliver/{id}/accept";
    logger.debug("Start calling acceptDeliverOrder");
    return this.orderWebClient
        .post()
        .uri(uriBuilder -> uriBuilder.path(path).build(id))
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .toBodilessEntity()
        .then();
  }

  @Override
  public Mono<Void> acceptJourneyOrder(String id) {
    String path = "/orders/journey/{id}/accept";
    logger.debug("Start calling acceptJourneyOrder");
    return this.orderWebClient
        .post()
        .uri(uriBuilder -> uriBuilder.path(path).build(id))
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .toBodilessEntity()
        .then();
  }
}
