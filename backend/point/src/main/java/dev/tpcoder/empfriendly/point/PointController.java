package dev.tpcoder.empfriendly.point;

import dev.tpcoder.empfriendly.point.model.dto.DeductRequest;
import dev.tpcoder.empfriendly.point.model.dto.TopupRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/points")
@RestController
@RequiredArgsConstructor
public class PointController {

  private final PointService pointService;

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
}
