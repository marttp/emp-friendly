package dev.tpcoder.empfriendly.management.restaurant;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RestaurantService {

  Flux<Restaurant> getRestaurants();

  Mono<Restaurant> createRestaurant(Restaurant restaurant);

  Mono<Void> deleteRestaurant(String restaurantId);
}
