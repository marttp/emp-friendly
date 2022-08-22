package dev.tpcoder.empfriendly.point;

import dev.tpcoder.empfriendly.point.model.Point;
import dev.tpcoder.empfriendly.point.model.PointChangeHistory;
import dev.tpcoder.empfriendly.point.model.dto.DeductRequest;
import dev.tpcoder.empfriendly.point.model.dto.TopupRequest;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/points")
@RestController
@RequiredArgsConstructor
public class PointController {

  private final PointService pointService;

  @GetMapping("/{referenceId}")
  public ResponseEntity<Point> getPoint(@PathVariable @NotBlank String referenceId) {
    var point = pointService.getPointByReferenceId(referenceId);
    return ResponseEntity.ok(point);
  }

  @PostMapping("/topup")
  public ResponseEntity<?> topup(@RequestBody @Valid TopupRequest body) {
    pointService.topUpPoint(body);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/deduct")
  public ResponseEntity<?> deduct(@RequestBody @Valid DeductRequest body) {
    pointService.deductPoint(body);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/history/{refId}")
  public List<PointChangeHistory> getTransactionList(@PathVariable String refId) {
    return pointService.getListTransaction(refId);
  }
}
