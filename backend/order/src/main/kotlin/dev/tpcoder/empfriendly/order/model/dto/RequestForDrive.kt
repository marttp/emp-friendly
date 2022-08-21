package dev.tpcoder.empfriendly.order.model.dto

import dev.tpcoder.empfriendly.order.enumeration.DriveType

data class RequestForDrive(
    val referenceId: String,
    val requesterId: String,
    val driverId: String,
    val type: DriveType
)