package dev.tpcoder.empfriendly.general.point

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExternalPointService(@Qualifier("pointWebClient") private val pointWebClient: WebClient) :
    PointService {

    private val logger = LoggerFactory.getLogger(ExternalPointService::class.java)

    override fun getTransactionHistory(id: String): Flux<Transaction> {
        val path = "/points/history/$id"
        logger.debug("Get transaction history id: $id")
        return this.pointWebClient.get()
            .uri(path)
            .header("requestUid", UUID.randomUUID().toString())
            .retrieve()
            .bodyToFlux(Transaction::class.java)
    }

    override fun getPointByRefId(referenceId: String): Mono<Point> {
        logger.debug("Get getPointByRefId: $referenceId")
        val path = "/points/$referenceId"
        return this.pointWebClient.get()
            .uri(path)
            .header("requestUid", UUID.randomUUID().toString())
            .retrieve()
            .bodyToMono(Point::class.java)
    }
}