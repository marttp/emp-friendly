package dev.tpcoder.empfriendly.order.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@Document
data class OrderStatusTracking(
    @Id
    @Indexed
    var id: String? = null,

    @Indexed
    val orderType: String,

    @Indexed
    val orderId: String,

    val employeeId: String,

    val status: String,

    @CreatedDate
    var createdDate: LocalDateTime = LocalDateTime.now()
)