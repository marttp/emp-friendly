package dev.tpcoder.empfriendly.general.point

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.util.*

@Service
class ExternalPointService(@Qualifier("pointWebClient") private val pointWebClient: WebClient) :
    PointService {

    private val logger = LoggerFactory.getLogger(ExternalPointService::class.java)

    override fun getTransactionHistory(id: String): Flux<Transaction> {
        val path = "/history/{refId}"
        logger.debug("Get transaction history id: $id")
        return this.pointWebClient.get()
            .uri { uriBuilder -> uriBuilder.path(path).build(id) }
            .header("requestUid", UUID.randomUUID().toString())
            .retrieve()
            .bodyToFlux(Transaction::class.java)
    }
}