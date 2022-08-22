package dev.tpcoder.empfriendly.general.qr

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono

@Controller
@ResponseBody
@RequestMapping("/qr")
class QrController(private val qrService: QrService) {

    @GetMapping("/{restaurantId}")
    fun getQrCode(@PathVariable restaurantId: String): Mono<QrCodeResponse> {
        return qrService.getQrCode(restaurantId)
    }
}