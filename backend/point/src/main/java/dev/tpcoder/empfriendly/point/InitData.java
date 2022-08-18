package dev.tpcoder.empfriendly.point;

import dev.tpcoder.empfriendly.point.enumeration.PointOwnerType;
import dev.tpcoder.empfriendly.point.enumeration.PointProcessingType;
import dev.tpcoder.empfriendly.point.model.Point;
import dev.tpcoder.empfriendly.point.model.dto.TopupRequest;
import dev.tpcoder.empfriendly.point.repository.PointChangeHistoryRepository;
import dev.tpcoder.empfriendly.point.repository.PointRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InitData {

  private static final BigDecimal TOPUP_BASE = new BigDecimal("100.00");
  private final PointRepository pointRepository;
  private final PointChangeHistoryRepository pointChangeHistoryRepository;
  private final PointService pointService;

  public InitData(PointRepository pointRepository,
      PointChangeHistoryRepository pointChangeHistoryRepository,
      PointService pointService) {
    this.pointRepository = pointRepository;
    this.pointService = pointService;
    this.pointChangeHistoryRepository = pointChangeHistoryRepository;
  }

  @Bean
  CommandLineRunner createForceUserRestaurantPoint() {
    return args -> {
      log.info("Clean up data...");
      pointRepository.deleteAll();
      log.info("Clean point data success...");
      pointChangeHistoryRepository.deleteAll();
      log.info("Clean pointChangeHistory data success...");

      log.info("Initial data...");
      var userIdList = List.of(
          "d37b2d0b-c06d-429c-b56d-7465c3959993",
          "ddf5757a-ca21-41f4-b668-836d7755d70d",
          "1636a414-1f16-45b9-8e36-28507c108be9",
          "8985954f-7925-42c9-a1e5-e6b4bad5fd6c",
          "1b4a4de9-0eca-4c73-97c6-e1b9df06678e",
          "93e16962-57d0-4a27-b8f4-8db20f29b25a"
      );

      userIdList.stream()
          .map(id -> new Point(id, BigDecimal.ZERO, PointOwnerType.INDIVIDUAL.name()))
          .map(pointRepository::save)
          .map(point -> new TopupRequest(PointProcessingType.INDIVIDUAL_CREDIT,
              point.getReferenceId(), TOPUP_BASE))
          .forEach(pointService::topUpPoint);
      log.info("User Point Completed!");
      var restaurantIdList = List.of(
          "a3cbc4f1-3636-4db9-bb42-36c49ba655b9",
          "d72d77fb-e96d-4d9a-964d-f2bf605c7e0b",
          "c820da4c-d8de-4229-848b-33e5f183a22c",
          "f084e35a-f745-4c86-b0d5-aae81bd632a9",
          "d31710fc-ba84-4ef9-ae2e-3c8e38e1c84c"
      );

      restaurantIdList.stream()
          .map(id -> new Point(id, BigDecimal.ZERO, PointOwnerType.RESTAURANT.name()))
          .map(pointRepository::save)
          .map(point -> new TopupRequest(PointProcessingType.RESTAURANT_CREDIT,
              point.getReferenceId(), TOPUP_BASE))
          .forEach(pointService::topUpPoint);
      log.info("Restaurant Point Completed!");
    };
  }
}
