package com.api.estramipyme;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseConfig {
  @Bean
  public ApplicationRunner holaMundoRunner() {
    return args -> System.out.println("Hola Mundo");
  }
}
