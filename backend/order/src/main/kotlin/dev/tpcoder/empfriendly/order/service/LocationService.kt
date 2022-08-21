package dev.tpcoder.empfriendly.order.service

import dev.tpcoder.empfriendly.order.model.dto.RequestForDrive
import dev.tpcoder.empfriendly.order.model.dto.Tracking
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class LocationService(@Qualifier("locationWebClient") private val locationWebClient: WebClient) {

    fun createMockDrive(requestForDrive: RequestForDrive): Tracking? {
        return locationWebClient.post()
            .uri("/locations/mock/drive")
            .bodyValue(requestForDrive)
            .retrieve()
            .bodyToMono<Tracking>()
            .block()
    }
}