package dev.tpcoder.empfriendly.payment.payments

import dev.tpcoder.empfriendly.payment.enumeration.PaymentMethodEnum
import dev.tpcoder.empfriendly.payment.enumeration.PointProcessingTypeEnum
import dev.tpcoder.empfriendly.payment.payments.models.Payment
import dev.tpcoder.empfriendly.payment.payments.models.dto.PaymentBody
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class PaymentController(private val paymentService: PaymentService) {

    private val logger = LoggerFactory.getLogger(PaymentController::class.java)

    @PostMapping("/payments/restaurants/{id}")
    fun paymentToRestaurant(@PathVariable id: String, @RequestBody @Valid body: PaymentBody): ResponseEntity<Void> {
        logger.info("Payment body: $body")
        val payment = Payment()
        payment.from = body.from
        payment.point = body.point
        payment.to = id
        payment.method = PaymentMethodEnum.QR_CODE_SCAN.name
        payment.type = PointProcessingTypeEnum.INDIVIDUAL_DEBIT.name
        paymentService.createPayment(payment)
        return ResponseEntity.ok().build()
    }
}