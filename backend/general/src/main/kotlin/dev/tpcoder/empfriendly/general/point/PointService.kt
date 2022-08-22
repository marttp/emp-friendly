package dev.tpcoder.empfriendly.general.point

import reactor.core.publisher.Flux

interface PointService {

    fun getTransactionHistory(id: String): Flux<Transaction>
}