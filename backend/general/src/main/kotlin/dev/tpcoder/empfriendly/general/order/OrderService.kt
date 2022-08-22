package dev.tpcoder.empfriendly.general.order

import reactor.core.publisher.Mono

interface OrderService {

    fun createOrder(body: CreateOrder): Mono<Void>
}