package dev.tpcoder.empfriendly.payment

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(@Value("\${external.url.points}") private val pointUrl: String) {

    @Bean
    fun pointWebClient(): WebClient {
        return WebClient.create(pointUrl)
    }
}