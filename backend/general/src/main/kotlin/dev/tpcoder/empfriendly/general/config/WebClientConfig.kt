package dev.tpcoder.empfriendly.general.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebClientConfig {

    @Bean
    fun paymentWebClient(@Value("\${external.url.payment}") paymentBaseUrl: String): WebClient =
        webClientBuilder(paymentBaseUrl).build()

    @Bean
    fun orderWebClient(@Value("\${external.url.order}") orderBaseUrl: String): WebClient =
        webClientBuilder(orderBaseUrl).build()

    @Bean
    fun pointWebClient(@Value("\${external.url.point}") pointBaseUrl: String): WebClient =
        webClientBuilder(pointBaseUrl).build()

    @Bean
    fun qrWebClient(@Value("\${external.url.qr}") qrBaseUrl: String): WebClient =
        webClientBuilder(qrBaseUrl).build()

    @Bean
    fun ratingWebClient(@Value("\${external.url.rating}") ratingBaseUrl: String): WebClient =
        webClientBuilder(ratingBaseUrl).build()


    companion object {
        fun webClientBuilder(baseUrl: String): WebClient.Builder = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl(baseUrl)
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().compress(true).wiretap(true)
                )
            )
    }
}