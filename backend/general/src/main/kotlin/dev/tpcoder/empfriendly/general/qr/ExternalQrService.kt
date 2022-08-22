package dev.tpcoder.empfriendly.general.qr

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
class ExternalQrService(@Qualifier("qrWebClient") private val qrWebClient: WebClient) : QrService {

    private val logger = LoggerFactory.getLogger(ExternalQrService::class.java)

    override fun getQrCode(restaurantId: String): Mono<QrCodeResponse> {
        logger.debug("Get QR Code from restaurantId: $restaurantId")
        val path = "/restaurants/$restaurantId"
        return this.qrWebClient.get()
            .uri(path)
            .header("requestUid", UUID.randomUUID().toString())
            .retrieve()
            .bodyToMono(QrCodeResponse::class.java)
    }
}