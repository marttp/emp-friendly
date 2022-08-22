package dev.tpcoder.empfriendly.management.point;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalPointService implements PointService {

  private final Logger logger = LoggerFactory.getLogger(ExternalPointService.class);

  private final WebClient webClient;

  public ExternalPointService(@Qualifier("pointWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Void> topup(PointChangeRequest body) {
    logger.debug("Topup request: {}", body);
    return this.webClient.post()
        .uri("/points/topup")
        .header("requestUid", UUID.randomUUID().toString())
        .bodyValue(body)
        .retrieve()
        .toBodilessEntity()
        .then();
  }

  @Override
  public Mono<Void> deduct(PointChangeRequest body) {
    logger.debug("Deduct request: {}", body);
    return this.webClient.post()
        .uri("/points/deduct")
        .header("requestUid", UUID.randomUUID().toString())
        .bodyValue(body)
        .retrieve()
        .toBodilessEntity()
        .then();
  }
}
