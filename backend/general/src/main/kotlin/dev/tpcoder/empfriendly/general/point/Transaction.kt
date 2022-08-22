package dev.tpcoder.empfriendly.general.point

import java.math.BigDecimal
import java.time.LocalDateTime

data class Transaction(
    val point: BigDecimal,
    val balancePoint: BigDecimal,
    val createdDate: LocalDateTime
)
