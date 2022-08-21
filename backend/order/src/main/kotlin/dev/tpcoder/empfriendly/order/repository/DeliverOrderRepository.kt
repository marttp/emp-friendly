package dev.tpcoder.empfriendly.order.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import dev.tpcoder.empfriendly.order.model.DeliveryOrder
import org.springframework.stereotype.Repository

@Repository
interface DeliverOrderRepository : RedisDocumentRepository<DeliveryOrder, String> {

    fun findByDriverId(driverId: String): Collection<DeliveryOrder>

    fun findByDriverIdAndStatus(driverId: String, status: String): Collection<DeliveryOrder>
}