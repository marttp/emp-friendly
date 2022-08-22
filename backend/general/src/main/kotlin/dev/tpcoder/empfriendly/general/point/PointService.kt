package dev.tpcoder.empfriendly.general.point

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PointService {

    fun getTransactionHistory(id: String): Flux<Transaction>

    fun getPointByRefId(referenceId: String): Mono<Point>
}