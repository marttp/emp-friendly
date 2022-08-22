package dev.tpcoder.empfriendly.general.rating

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class RatingReceivedDTO(
    @JsonProperty("user_id")
    val userId: String,

    @JsonProperty("target_id")
    val targetId: String,

    val type: RatingTypeEnum,

    val rate: Int,

    @JsonProperty("created_date")
    val createdDate: LocalDateTime
)
