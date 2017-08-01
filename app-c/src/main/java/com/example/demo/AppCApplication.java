package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class AppCApplication {
	@Bean
	RestOperations restOperations() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppCApplication.class, args);
	}
}

@RestController
@AllArgsConstructor
class AppCController {
	private final RestOperations webClient;

	@GetMapping("/count")
	public String start(@RequestParam String word) {
		return String.valueOf(word.length());
	}
}