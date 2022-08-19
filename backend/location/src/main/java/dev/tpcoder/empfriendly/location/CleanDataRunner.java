package dev.tpcoder.empfriendly.location;

import dev.tpcoder.empfriendly.location.repository.LocationRepository;
import dev.tpcoder.empfriendly.location.repository.TrackingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleanDataRunner {

  @Bean
  CommandLineRunner cleanTrackingLocationData(TrackingRepository trackingRepository, LocationRepository locationRepository) {
    return args -> {
      trackingRepository.deleteAll();
      locationRepository.deleteAll();
    };
  }
}
