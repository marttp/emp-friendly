package dev.tpcoder.empfriendly.general.order

import javax.validation.constraints.NotNull

data class CreateOrder(
    var requesterId: String? = null,
    @NotNull
    val driverId: String,
    @NotNull
    val type: DriveType
)
