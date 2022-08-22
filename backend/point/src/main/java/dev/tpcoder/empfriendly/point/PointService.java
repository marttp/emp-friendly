package dev.tpcoder.empfriendly.point;

import static dev.tpcoder.empfriendly.point.enumeration.PointProcessingType.INDIVIDUAL_CREDIT;
import static dev.tpcoder.empfriendly.point.enumeration.PointProcessingType.INDIVIDUAL_DEBIT;
import static dev.tpcoder.empfriendly.point.enumeration.PointProcessingType.RESTAURANT_CREDIT;
import static dev.tpcoder.empfriendly.point.enumeration.PointProcessingType.RESTAURANT_DEBIT;

import dev.tpcoder.empfriendly.point.enumeration.PointOwnerType;
import dev.tpcoder.empfriendly.point.enumeration.PointProcessingType;
import dev.tpcoder.empfriendly.point.model.Point;
import dev.tpcoder.empfriendly.point.model.PointChangeHistory;
import dev.tpcoder.empfriendly.point.model.dto.DeductRequest;
import dev.tpcoder.empfriendly.point.model.dto.TopupRequest;
import dev.tpcoder.empfriendly.point.repository.PointChangeHistoryRepository;
import dev.tpcoder.empfriendly.point.repository.PointRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

  private static final Set<PointProcessingType> CREDIT_SET =
      Set.of(INDIVIDUAL_CREDIT, RESTAURANT_CREDIT);
  private static final Set<PointProcessingType> DEBIT_SET =
      Set.of(INDIVIDUAL_DEBIT, RESTAURANT_DEBIT);
  private static final Set<PointProcessingType> VALID_INDIVIDUAL_SET =
      Set.of(INDIVIDUAL_DEBIT, INDIVIDUAL_CREDIT);
  private static final Set<PointProcessingType> VALID_RESTAURANT_SET =
      Set.of(RESTAURANT_CREDIT, RESTAURANT_DEBIT);

  private final PointRepository pointRepository;
  private final PointChangeHistoryRepository pointChangeHistoryRepository;

  public Point getPointByReferenceId(@NonNull String referenceId) {
    return pointRepository.findById(referenceId)
        .orElseThrow(() -> new RuntimeException("Point reference not found"));
  }

  public void deductPoint(DeductRequest body) {
    if (body.point().compareTo(BigDecimal.ZERO) < 0) {
      throw new RuntimeException("Can't Negative");
    }
    if (!DEBIT_SET.contains(body.type())) {
      throw new RuntimeException("Invalid operation");
    }
    var point = pointRepository.findById(body.referenceId())
        .orElseThrow(() -> new RuntimeException("Point reference not found"));
    if (!checkValidSet(point, body.type())) {
      throw new RuntimeException("Invalid processing type");
    }
    var deduct = body.point();
    var balance = point.getCurrent().subtract(deduct);
    if (balance.compareTo(BigDecimal.ZERO) < 0) {
      throw new RuntimeException("Current balance insufficient");
    }
    var log = createHistory(body.referenceId(), deduct, balance);
    point.setCurrent(balance);
    pointChangeHistoryRepository.save(log);
    pointRepository.save(point);
  }

  public void topUpPoint(TopupRequest body) {
    if (body.point().compareTo(BigDecimal.ZERO) < 0) {
      throw new RuntimeException("Can't Negative");
    }
    if (!CREDIT_SET.contains(body.type())) {
      throw new RuntimeException("Invalid operation");
    }
    var point = pointRepository.findById(body.referenceId())
        .orElseThrow(() -> new RuntimeException("Point reference not found"));
    if (!checkValidSet(point, body.type())) {
      throw new RuntimeException("Invalid processing type");
    }
    var topup = body.point();
    var balance = point.getCurrent().add(topup);
    var log = createHistory(body.referenceId(), topup, balance);
    point.setCurrent(balance);
    pointChangeHistoryRepository.save(log);
    pointRepository.save(point);
  }

  public List<PointChangeHistory> getListTransaction(String refId) {
    return pointChangeHistoryRepository.findByReferenceIdOrderByCreatedDateDesc(refId);
  }

  private PointChangeHistory createHistory(String refId, BigDecimal point,
      BigDecimal currentBalance) {
    var newHistory = new PointChangeHistory();
    newHistory.setPoint(point)
        .setBalancePoint(currentBalance)
        .setReferenceId(refId);
    return newHistory;
  }

  private boolean checkValidSet(Point point, PointProcessingType type) {
    Set<PointProcessingType> selectedSet = Set.of();
    if (PointOwnerType.valueOf(point.getType()) == PointOwnerType.INDIVIDUAL) {
      selectedSet = VALID_INDIVIDUAL_SET;
    } else if (PointOwnerType.valueOf(point.getType()) == PointOwnerType.RESTAURANT) {
      selectedSet = VALID_RESTAURANT_SET;
    }
    return selectedSet.contains(type);
  }
}
