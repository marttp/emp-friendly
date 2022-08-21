package dev.tpcoder.empfriendly.order.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(@Value("\${external.url.location}") private val locationUrl: String) {

    @Bean
    fun locationWebClient(): WebClient = WebClient.create(locationUrl)
}