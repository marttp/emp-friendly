package dev.tpcoder.empfriendly.management.point;

import reactor.core.publisher.Mono;

public interface PointService {
  Mono<Void> topup(PointChangeRequest body);
  Mono<Void> deduct(PointChangeRequest body);
}
