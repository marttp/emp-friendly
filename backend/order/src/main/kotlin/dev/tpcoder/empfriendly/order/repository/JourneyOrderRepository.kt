package dev.tpcoder.empfriendly.order.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import dev.tpcoder.empfriendly.order.model.JourneyOrder
import org.springframework.stereotype.Repository

@Repository
interface JourneyOrderRepository : RedisDocumentRepository<JourneyOrder, String> {

    fun findByDriverId(driverId: String): Collection<JourneyOrder>

    fun findByDriverIdAndStatus(driverId: String, status: String): Collection<JourneyOrder>
}