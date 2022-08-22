package dev.tpcoder.empfriendly.driver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

  private final String locationBaseUrl;
  private final String orderBaseUrl;

  public WebClientConfig(@Value("${external.url.location}") String locationBaseUrl,
      @Value("${external.url.order}") String orderBaseUrl) {
    this.locationBaseUrl = locationBaseUrl;
    this.orderBaseUrl = orderBaseUrl;
  }

  @Bean
  WebClient locationWebClient() {
    return WebClient.builder()
        .baseUrl(locationBaseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create().compress(true).wiretap(true)))
        .build();
  }

  @Bean
  WebClient orderWebClient() {
    return WebClient.builder()
        .baseUrl(orderBaseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create().compress(true).wiretap(true)))
        .build();
  }
}
