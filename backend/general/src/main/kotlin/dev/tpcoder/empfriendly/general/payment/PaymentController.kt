package dev.tpcoder.empfriendly.general.payment

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller
@ResponseBody
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/payments/restaurants/{id}")
    fun paymentToRestaurant(
        @RequestHeader("user-id") userId: String,
        @PathVariable id: String,
        @RequestBody @Valid body: PaymentBody,
    ): Mono<Void> {
        body.to = id
        body.from = userId
        return paymentService.payToRestaurant(body)
            .then()
    }
}