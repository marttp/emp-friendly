package dev.tpcoder.empfriendly.payment.payments.models

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDateTime


@Document
class Payment {

    @Id
    @Indexed
    var id: String? = null

    @Indexed
    lateinit var from: String

    @Indexed
    lateinit var to: String

    lateinit var point: BigDecimal

    @Indexed
    lateinit var method: String

    @Indexed
    lateinit var type: String

    @CreatedDate
    @Indexed
    lateinit var createdDate: LocalDateTime

}