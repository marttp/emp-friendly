package dev.tpcoder.empfriendly.order.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import dev.tpcoder.empfriendly.order.model.OrderStatusTracking
import org.springframework.stereotype.Repository

@Repository
interface OrderStatusTrackingRepository : RedisDocumentRepository<OrderStatusTracking, String> {

    fun findByOrderIdOrderByCreatedDateDesc(orderId: String): Collection<OrderStatusTracking>

}