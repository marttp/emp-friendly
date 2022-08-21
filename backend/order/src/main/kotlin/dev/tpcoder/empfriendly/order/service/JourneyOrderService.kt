package dev.tpcoder.empfriendly.order.service

import dev.tpcoder.empfriendly.order.Constants
import dev.tpcoder.empfriendly.order.enumeration.DriveType
import dev.tpcoder.empfriendly.order.enumeration.OrderStatus
import dev.tpcoder.empfriendly.order.model.JourneyOrder
import dev.tpcoder.empfriendly.order.model.OrderStatusTracking
import dev.tpcoder.empfriendly.order.model.dto.CreateOrder
import dev.tpcoder.empfriendly.order.model.dto.RequestForDrive
import dev.tpcoder.empfriendly.order.repository.JourneyOrderRepository
import dev.tpcoder.empfriendly.order.repository.OrderStatusTrackingRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JourneyOrderService(
    private val journeyOrderRepository: JourneyOrderRepository,
    private val orderStatusTrackingRepository: OrderStatusTrackingRepository,
    private val locationService: LocationService
) {

    private final val logger = LoggerFactory.getLogger(JourneyOrderService::class.java)

    fun getJourneyOrder(driverId: String): List<JourneyOrder> {
        return journeyOrderRepository.findByDriverIdAndStatus(driverId, OrderStatus.WAITING.name)
            .stream()
            .toList()
    }

    fun createJourneyOrder(body: CreateOrder) {
        val newJourney = JourneyOrder(
            id = Constants.createJourneyId(),
            requesterId = body.requesterId,
            driverId = body.driverId,
            status = OrderStatus.WAITING.name
        )
        journeyOrderRepository.save(newJourney)
        val track = OrderStatusTracking(
            orderId = newJourney.id!!,
            employeeId = body.requesterId,
            orderType = DriveType.JOURNEY.name,
            status = OrderStatus.WAITING.name
        )
        orderStatusTrackingRepository.save(track)
    }

    fun acceptJourneyOrder(id: String) {
        val optionalOrder = journeyOrderRepository.findById(id)
        if (optionalOrder.isEmpty) {
            throw RuntimeException("JourneyId Not Found")
        }
        val order = optionalOrder.get()
        order.status = OrderStatus.ACCEPTED.name
        journeyOrderRepository.save(order)
        val track = OrderStatusTracking(
            orderId = order.id!!,
            employeeId = order.driverId,
            orderType = DriveType.JOURNEY.name,
            status = order.status
        )
        orderStatusTrackingRepository.save(track)
        val requestFroDrive = RequestForDrive(
            referenceId = order.id,
            requesterId = order.requesterId,
            driverId = order.driverId,
            type = DriveType.JOURNEY
        )
        val trackingResult = locationService.createMockDrive(requestFroDrive)
        logger.info("Tracking from location: $trackingResult")
    }
}