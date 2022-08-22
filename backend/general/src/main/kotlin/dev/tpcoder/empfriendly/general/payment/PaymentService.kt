package dev.tpcoder.empfriendly.general.payment

import reactor.core.publisher.Mono

interface PaymentService {
    fun payToRestaurant(body: PaymentBody): Mono<Void>
}