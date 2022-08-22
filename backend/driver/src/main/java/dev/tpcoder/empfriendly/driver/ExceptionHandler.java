package dev.tpcoder.empfriendly.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tpcoder.empfriendly.driver.model.ErrorResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

  private final ObjectMapper objectMapper;

  public ExceptionHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    return Mono.defer(() -> {
      try {
        final ServerHttpResponse response = exchange.getResponse();
        final HttpHeaders headers = response.getHeaders();
        final ErrorResponse error = ErrorResponse.serverError(ex.getMessage());

        response.setStatusCode(HttpStatus.valueOf(error.errorStatus()));
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));

        final String json = objectMapper.writeValueAsString(error);
        final DataBuffer buffer = response.bufferFactory()
            .wrap(json.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer))
            .doOnError(e -> DataBufferUtils.release(buffer));
      } catch (final Exception e) {
        return Mono.error(e);
      }
    });
  }
}
