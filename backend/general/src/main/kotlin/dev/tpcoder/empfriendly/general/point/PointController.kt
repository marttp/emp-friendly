package dev.tpcoder.empfriendly.general.point

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono

@Controller
@ResponseBody
@RequestMapping("/points")
class PointController(private val pointService: PointService) {

    @GetMapping("/me")
    fun getPoint(@RequestHeader("user-id") userId: String): Mono<Point> {
        return pointService.getPointByRefId(userId)
    }
}