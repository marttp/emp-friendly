package dev.tpcoder.empfriendly.general.order

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ExternalOrderService(@Qualifier("orderWebClient") private val orderWebClient: WebClient) :
    OrderService {

    private val logger = LoggerFactory.getLogger(ExternalOrderService::class.java)

    override fun createOrder(body: CreateOrder): Mono<Void> {
        logger.debug("Start calling createOrder")
        return this.orderWebClient
            .post()
            .uri("/orders")
            .bodyValue(body)
            .retrieve()
            .toBodilessEntity()
            .then()
    }
}