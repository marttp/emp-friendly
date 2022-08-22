package dev.tpcoder.empfriendly.general.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

@Component
@Order(-2)
class ExceptionHandler(private val objectMapper: ObjectMapper) : WebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        return Mono.defer {
            try {
                val response =
                    exchange.response
                val headers = response.headers
                val error: ErrorResponse = ErrorResponse.serverError(ex.message)
                response.statusCode = HttpStatus.valueOf(error.errorStatus())
                headers[HttpHeaders.CONTENT_TYPE] = listOf(MediaType.APPLICATION_JSON_VALUE)
                val json = objectMapper.writeValueAsString(error)
                val buffer = response.bufferFactory()
                    .wrap(json.toByteArray(StandardCharsets.UTF_8))
                val monoBuffer = Mono.just(buffer)
                return@defer response.writeWith(monoBuffer)
                    .doOnError { DataBufferUtils.release(buffer) }
            } catch (e: Exception) {
                return@defer Mono.error<Void>(e)
            }
        }
    }
}