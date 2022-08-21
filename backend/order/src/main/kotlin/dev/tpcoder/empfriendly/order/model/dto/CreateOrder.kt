package dev.tpcoder.empfriendly.order.model.dto

import dev.tpcoder.empfriendly.order.enumeration.DriveType
import javax.validation.constraints.NotNull

data class CreateOrder(
    @NotNull
    val requesterId: String,
    @NotNull
    val driverId: String,
    @NotNull
    val type: DriveType
)
