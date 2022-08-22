package dev.tpcoder.empfriendly.management.restaurant;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalRestaurantService implements RestaurantService {

  private static final String PATH = "/restaurants";
  private final WebClient webClient;
  private final Logger logger = LoggerFactory.getLogger(ExternalRestaurantService.class);

  public ExternalRestaurantService(
      @Qualifier("restaurantManagementWebClient") WebClient restaurantManagementWebClient) {
    this.webClient = restaurantManagementWebClient;
  }

  @Override
  public Flux<Restaurant> getRestaurants() {
    logger.debug("Get Restaurants");
    return this.webClient.get()
        .uri(PATH)
        .retrieve()
        .bodyToFlux(Restaurant.class);
  }

  @Override
  public Mono<Restaurant> createRestaurant(CreateRestaurantRequest restaurant) {
    logger.debug("Create Restaurants");
    logger.debug("body: {}", restaurant);
    return this.webClient.post()
        .uri(PATH)
        .header("requestUid", UUID.randomUUID().toString())
        .bodyValue(restaurant)
        .retrieve()
        .bodyToMono(Restaurant.class);
  }

  @Override
  public Mono<Void> deleteRestaurant(String restaurantId) {
    String path = PATH + "/{restaurantId}";
    return this.webClient.delete()
        .uri(uriBuilder -> uriBuilder.path(path).build(restaurantId))
        .header("requestUid", UUID.randomUUID().toString())
        .retrieve()
        .toBodilessEntity()
        .then();
  }
}
