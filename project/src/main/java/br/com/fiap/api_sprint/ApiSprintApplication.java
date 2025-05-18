package br.com.fiap.api_sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiSprintApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiSprintApplication.class, args);
  }

}
