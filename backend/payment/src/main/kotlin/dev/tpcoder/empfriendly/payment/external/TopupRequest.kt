package dev.tpcoder.empfriendly.payment.external

import dev.tpcoder.empfriendly.payment.enumeration.PointProcessingTypeEnum
import java.math.BigDecimal

data class PointBaseRequest(
     val type: PointProcessingTypeEnum,
     val referenceId: String,
     val point: BigDecimal
)
