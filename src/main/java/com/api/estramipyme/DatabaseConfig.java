package com.api.estramipyme;
import com.api.estramipyme.models.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseConfig {
  @Bean
  public ApplicationRunner holaMundoRunner() {
    return args -> System.out.println("Cargar la base de datos");
  }
}
