package dev.tpcoder.empfriendly.general.point

import java.math.BigDecimal

data class Point(
    val referenceId: String,
    val current: BigDecimal,
    val type: String
)
