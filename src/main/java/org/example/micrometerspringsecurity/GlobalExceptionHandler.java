package org.example.micrometerspringsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler implements WebExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    // that's the kind of exception we're interested in
    if (ex instanceof IllegalStateException) {
      LOGGER.error("Caught exception: {}", ex.getMessage(), ex);
    } else {
      LOGGER.error("Caught exception: {}", ex.getMessage());
    }
    return Mono.empty();
  }

}
