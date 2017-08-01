package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@EnableEurekaClient
@SpringBootApplication
public class AppBApplication {
	@Bean
	RestOperations restOperations() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppBApplication.class, args);
	}
}

@RestController
@AllArgsConstructor
class AppBController {
	private final RestOperations webClient;
	private final MyEventsSource eventsSource;

	@GetMapping("/hello")
	public String start(@RequestParam String name) {
		String count = webClient.getForObject("http://localhost:8083/count?word=" + name, String.class);

		MyEvent event = new MyEvent(UUID.randomUUID().toString(), String.format("%s(%s)", name, count));
		eventsSource.eventsChannel().send(new GenericMessage<>(event));

		return "Hello, " + name;
	}
}