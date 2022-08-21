package dev.tpcoder.empfriendly.order.service

import dev.tpcoder.empfriendly.order.model.OrderStatusTracking
import dev.tpcoder.empfriendly.order.repository.OrderStatusTrackingRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderStatusTrackingRepository: OrderStatusTrackingRepository) {

    fun getOrderStatusTrackingByOrderId(orderId: String): List<OrderStatusTracking> {
        return orderStatusTrackingRepository.findByOrderIdOrderByCreatedDateDesc(orderId)
            .stream()
            .toList()
    }
}