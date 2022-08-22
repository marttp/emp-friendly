package dev.tpcoder.empfriendly.general.payment

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class PaymentBody(
    var from: String? = null,

    @JsonIgnore
    var to: String? = null,

    @NotNull
    var point: BigDecimal
)