package dev.tpcoder.empfriendly.management.restaurant;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
  private final RestaurantService restaurantService;

  @QueryMapping
  public Flux<Restaurant> restaurants() {
    return restaurantService.getRestaurants();
  }

  @MutationMapping
  public Mono<Restaurant> createRestaurant(@Argument String restaurantId, @Argument String name) {
    CreateRestaurantRequest newRestaurant = new CreateRestaurantRequest(restaurantId, name);
    return restaurantService.createRestaurant(newRestaurant);
  }

  @MutationMapping
  public Mono<String> deleteRestaurant(@Argument String restaurantId) {
    return restaurantService.deleteRestaurant(restaurantId)
        .thenReturn("OK");
  }
}
