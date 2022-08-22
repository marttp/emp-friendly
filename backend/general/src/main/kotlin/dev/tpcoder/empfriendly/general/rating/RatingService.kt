package dev.tpcoder.empfriendly.general.rating

import reactor.core.publisher.Mono

interface RatingService {
    fun sendRating(body: RatingReceivedDTO): Mono<Void>
    fun getRatingById(id: String): Mono<Rating>
}