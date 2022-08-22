package dev.tpcoder.empfriendly.general.point

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class TransactionController(private val pointService: PointService) {

    @QueryMapping
    fun getTransactionHistory(@Argument refId: String): Flux<Transaction> {
        return pointService.getTransactionHistory(refId)
    }
}