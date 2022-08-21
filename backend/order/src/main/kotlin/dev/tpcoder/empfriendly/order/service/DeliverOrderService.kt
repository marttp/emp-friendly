package dev.tpcoder.empfriendly.order.service

import dev.tpcoder.empfriendly.order.Constants
import dev.tpcoder.empfriendly.order.enumeration.DriveType
import dev.tpcoder.empfriendly.order.enumeration.OrderStatus
import dev.tpcoder.empfriendly.order.model.DeliveryOrder
import dev.tpcoder.empfriendly.order.model.OrderStatusTracking
import dev.tpcoder.empfriendly.order.model.dto.CreateOrder
import dev.tpcoder.empfriendly.order.model.dto.RequestForDrive
import dev.tpcoder.empfriendly.order.repository.DeliverOrderRepository
import dev.tpcoder.empfriendly.order.repository.OrderStatusTrackingRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DeliverOrderService(
    private val deliverOrderRepository: DeliverOrderRepository,
    private val orderStatusTrackingRepository: OrderStatusTrackingRepository,
    private val locationService: LocationService
) {

    private final val logger = LoggerFactory.getLogger(DeliveryOrder::class.java)

    fun getDeliverOrder(driverId: String): List<DeliveryOrder>  {
        return deliverOrderRepository.findByDriverIdAndStatus(driverId, OrderStatus.WAITING.name)
            .stream()
            .toList()
    }

    fun createDeliverOrder(body: CreateOrder) {
        val newDeliver = DeliveryOrder(
            id = Constants.createDeliveryId(),
            requesterId = body.requesterId,
            driverId = body.driverId,
            status = OrderStatus.WAITING.name
        )
        deliverOrderRepository.save(newDeliver)
        val track = OrderStatusTracking(
            orderId = newDeliver.id!!,
            employeeId = body.requesterId,
            orderType = DriveType.DELIVER.name,
            status = OrderStatus.WAITING.name
        )
        orderStatusTrackingRepository.save(track)
    }

    fun acceptDeliverOrder(id: String) {
        val optionalOrder = deliverOrderRepository.findById(id)
        if (optionalOrder.isEmpty) {
            throw RuntimeException("DeliverId Not Found")
        }
        val order = optionalOrder.get()
        order.status = OrderStatus.ACCEPTED.name
        deliverOrderRepository.save(order)
        val track = OrderStatusTracking(
            orderId = order.id!!,
            employeeId = order.driverId,
            orderType = DriveType.DELIVER.name,
            status = order.status
        )
        orderStatusTrackingRepository.save(track)
        val requestFroDrive = RequestForDrive(
            referenceId = order.id,
            requesterId = order.requesterId,
            driverId = order.driverId,
            type = DriveType.DELIVER
        )
        val trackingResult = locationService.createMockDrive(requestFroDrive)
        logger.info("Tracking from location: $trackingResult")
    }
}