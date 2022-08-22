package dev.tpcoder.empfriendly.general.qr

import reactor.core.publisher.Mono

interface QrService {

    fun getQrCode(restaurantId: String): Mono<QrCodeResponse>
}