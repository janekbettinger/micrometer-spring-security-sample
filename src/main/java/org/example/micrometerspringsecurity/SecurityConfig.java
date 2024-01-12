package org.example.micrometerspringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
    return httpSecurity.authorizeExchange(
            config -> config.pathMatchers("/actuator/health").permitAll().anyExchange().denyAll())
        .build();
  }

}
