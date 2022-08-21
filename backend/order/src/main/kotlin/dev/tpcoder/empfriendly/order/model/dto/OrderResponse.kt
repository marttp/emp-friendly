package dev.tpcoder.empfriendly.order.model.dto

import dev.tpcoder.empfriendly.order.enumeration.DriveType
import java.time.LocalDateTime

data class OrderResponse(
    val id: String,
    val type: DriveType,
    val createdDate: LocalDateTime
)
