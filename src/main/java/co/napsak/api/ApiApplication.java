package co.napsak.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.time.LocalDateTime;

@EnableWebFlux
@SpringBootApplication
@Slf4j
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		log.info("Application started on {}", LocalDateTime.now());
	}
}
