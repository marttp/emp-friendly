package dev.tpcoder.empfriendly.general.order

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller
@ResponseBody
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    private val logger = LoggerFactory.getLogger(OrderController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun createOrder(
        @RequestHeader("user-id") userId: String,
        @RequestBody @Valid body: CreateOrder
    ): Mono<Void> {
        body.requesterId = userId
        logger.debug("createOrder: $body")
        return orderService.createOrder(body).then()
    }
}