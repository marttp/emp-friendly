package dev.tpcoder.empfriendly.general.payment

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExternalPaymentService(@Qualifier("paymentWebClient") private val paymentWebClient: WebClient) :
    PaymentService {

    private val logger = LoggerFactory.getLogger(ExternalPaymentService::class.java)

    override fun payToRestaurant(body: PaymentBody): Mono<Void> {
        logger.debug("Pay to Restaurant Body: $body")
        val path = "/payments/restaurants/${body.to}"
        return this.paymentWebClient.post()
            .uri(path)
            .header("requestUid", UUID.randomUUID().toString())
            .bodyValue(body)
            .retrieve()
            .toBodilessEntity()
            .then()
    }
}