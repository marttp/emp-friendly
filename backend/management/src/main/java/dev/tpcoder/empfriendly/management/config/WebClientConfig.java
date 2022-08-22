package dev.tpcoder.empfriendly.management.config;

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

  @Bean
  WebClient.Builder webBuilder() {
    return WebClient.builder()
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create().compress(true).wiretap(true)));
  }

  @Bean
  WebClient employeeWebClient(WebClient.Builder builder,
      @Value("${external.url.employee}") String employeeBaseUrl) {
    return builder.baseUrl(employeeBaseUrl).build();
  }

  @Bean
  WebClient pointWebClient(WebClient.Builder builder,
      @Value("${external.url.point}") String pointBaseUrl) {
    return builder.baseUrl(pointBaseUrl).build();
  }

  @Bean
  WebClient restaurantManagementWebClient(WebClient.Builder builder,
      @Value("${external.url.restaurant-management}") String restaurantManagementBaseUrl) {
    return builder.baseUrl(restaurantManagementBaseUrl).build();
  }

}
