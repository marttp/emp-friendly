package dev.tpcoder.empfriendly.general.rating

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Controller
class RatingController(private val ratingService: RatingService) {

    @QueryMapping
    fun getRatingById(@Argument id: String): Mono<Rating> {
        return ratingService.getRatingById(id)
    }

    @MutationMapping
    fun sendRating(
        @Argument id: String,
        @Argument fromId: String,
        @Argument type: RatingTypeEnum,
        @Argument rating: Int
    ): Mono<Boolean> {
        val ratingDto = RatingReceivedDTO(
            userId = fromId,
            targetId = id,
            type = type,
            rate = rating,
            createdDate = LocalDateTime.now()
        )
        return ratingService.sendRating(ratingDto)
            .onErrorResume { Mono.error(RuntimeException("Error Send Rating")) }
            .thenReturn(true)
    }
}