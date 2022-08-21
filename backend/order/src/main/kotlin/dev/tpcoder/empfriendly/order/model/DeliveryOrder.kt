package dev.tpcoder.empfriendly.order.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@Document
data class DeliveryOrder(
    @Id
    @Indexed
    val id: String? = null,

    @Indexed
    var status: String,

    @Indexed
    val requesterId: String,

    @Indexed
    val driverId: String,

    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.now()
)