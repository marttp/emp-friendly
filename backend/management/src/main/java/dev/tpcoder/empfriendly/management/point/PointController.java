package dev.tpcoder.empfriendly.management.point;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {

  private final PointService pointService;

  @PostMapping("/topup")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> topup(@RequestBody @Valid PointChangeRequest body) {
    return pointService.topup(body).then();
  }

  @PostMapping("/deduct")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deduct(@RequestBody @Valid PointChangeRequest body) {
    return pointService.deduct(body).then();
  }

  @GetMapping("/{refId}")
  public Mono<Point> getPointByRefId(@PathVariable @NotBlank String refId) {
    return pointService.getPointByRefId(refId);
  }
}
