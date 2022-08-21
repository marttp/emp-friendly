package dev.tpcoder.empfriendly.order

import dev.tpcoder.empfriendly.order.enumeration.DriveType
import dev.tpcoder.empfriendly.order.model.OrderStatusTracking
import dev.tpcoder.empfriendly.order.model.dto.CreateOrder
import dev.tpcoder.empfriendly.order.model.dto.OrderResponse
import dev.tpcoder.empfriendly.order.service.DeliverOrderService
import dev.tpcoder.empfriendly.order.service.JourneyOrderService
import dev.tpcoder.empfriendly.order.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class OrderController(
    private val deliverOrderService: DeliverOrderService,
    private val journeyOrderService: JourneyOrderService,
    private val orderService: OrderService
) {

    private final val logger = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/orders")
    fun getOrdersByDriverId(@RequestParam id: String): List<OrderResponse> {
        val responseList = mutableListOf<OrderResponse>()
        val deliverList = deliverOrderService.getDeliverOrder(id)
        val deliverResponse =
            deliverList.map { OrderResponse(it.id!!, DriveType.DELIVER, it.createdDate) }
        responseList.addAll(deliverResponse)
        val journeyList = journeyOrderService.getJourneyOrder(id)
        val journeyResponse =
            journeyList.map { OrderResponse(it.id!!, DriveType.JOURNEY, it.createdDate) }
        responseList.addAll(journeyResponse)
        responseList.sortBy { it.createdDate }
        return responseList
    }

    @PostMapping("/orders")
    fun createOrder(@RequestBody @Valid body: CreateOrder) {
        logger.info("Request create order: $body")
        if (body.type == DriveType.DELIVER) {
            logger.info("Create DELIVER Order")
            deliverOrderService.createDeliverOrder(body)
        } else if (body.type == DriveType.JOURNEY) {
            logger.info("Create JOURNEY Order")
            journeyOrderService.createJourneyOrder(body)
        }
        logger.info("Completed Create Order")
    }

    @PostMapping("/orders/deliver/{id}/accept")
    fun acceptDeliverOrder(@PathVariable id: String) {
        logger.info("Accept deliverId: $id")
        deliverOrderService.acceptDeliverOrder(id)
    }

    @PostMapping("/orders/journey/{id}/accept")
    fun acceptJourneyOrder(@PathVariable id: String) {
        logger.info("Accept journeyId: $id")
        journeyOrderService.acceptJourneyOrder(id)
    }

    @GetMapping("/orders/{orderId}/tracking")
    fun getStatusTracking(@PathVariable orderId: String): List<OrderStatusTracking> {
        return orderService.getOrderStatusTrackingByOrderId(orderId)
    }

}