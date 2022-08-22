package dev.tpcoder.empfriendly.general.rating

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExternalRatingService(@Qualifier("ratingWebClient") private val ratingWebClient: WebClient) :
    RatingService {

    private val logger = LoggerFactory.getLogger(ExternalRatingService::class.java)

    override fun sendRating(body: RatingReceivedDTO): Mono<Void> {
        logger.debug("Send rating: $body")
        return this.ratingWebClient
            .post()
            .uri("/ratings")
            .header("requestUid", UUID.randomUUID().toString())
            .bodyValue(body)
            .retrieve()
            .toBodilessEntity()
            .then()
    }

    override fun getRatingById(id: String): Mono<Rating> {
        logger.debug("Get Rating of ID: $id")
        return this.ratingWebClient
            .get()
            .uri { uriBuilder -> uriBuilder.path("/ratings/{id}").build(id) }
            .header("requestUid", UUID.randomUUID().toString())
            .retrieve()
            .bodyToMono(Rating::class.java)
    }
}