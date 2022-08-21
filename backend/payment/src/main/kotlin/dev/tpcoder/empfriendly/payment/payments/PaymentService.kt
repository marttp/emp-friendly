package dev.tpcoder.empfriendly.payment.payments

import dev.tpcoder.empfriendly.payment.enumeration.PointProcessingTypeEnum
import dev.tpcoder.empfriendly.payment.external.PointBaseRequest
import dev.tpcoder.empfriendly.payment.payments.models.Payment
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class PaymentService(private val paymentRepository: PaymentRepository,
                     @Qualifier("pointWebClient") private val pointWebClient: WebClient) {

    fun createPayment(body: Payment) {
        paymentRepository.save(body)
        val topupBody = PointBaseRequest(
            type=PointProcessingTypeEnum.RESTAURANT_CREDIT,
            referenceId = body.to,
            point = body.point
        )
        val topup = pointWebClient.post()
            .uri("/topup")
            .bodyValue(topupBody)
            .retrieve()
            .toBodilessEntity()
        val deductBody = PointBaseRequest(
            type=PointProcessingTypeEnum.INDIVIDUAL_DEBIT,
            referenceId = body.from,
            point = body.point
        )
        val deduct = pointWebClient.post()
            .uri("/deduct")
            .bodyValue(deductBody)
            .retrieve()
            .toBodilessEntity()
        // Simple Call
        Mono.zip(topup, deduct).block()
    }
}