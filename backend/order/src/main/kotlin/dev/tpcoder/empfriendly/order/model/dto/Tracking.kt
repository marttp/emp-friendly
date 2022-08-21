package dev.tpcoder.empfriendly.order.model.dto


data class Tracking(
    val id: String,
    val requesterId: String,
    val driverId: String,
    val type: String
)