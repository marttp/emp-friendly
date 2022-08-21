package dev.tpcoder.empfriendly.payment.payments.models.dto

import java.math.BigDecimal

data class PaymentBody(
    var from: String,
    var point: BigDecimal
)
