package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class AppAApplication {
	@Bean
	RestOperations restOperations() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppAApplication.class, args);
	}
}

@RestController
class AppAController {
	@Autowired private Service1 service1;

	@GetMapping("/start")
	public List<String> start(@RequestParam String data) {
		return service1.prepareData(data);
	}
}

@Service
@Slf4j
class Service1 {
	@Autowired private RestOperations webClient;

	List<String> prepareData(String data) {
		int length = webClient.getForObject("http://localhost:8082/count?word=" + data, int.class);
		log.info("Preparing data for length=" + length);
		return IntStream.range(1, length+1)
				.mapToObj(v -> data.substring(0, v))
				.collect(Collectors.toList());
	}
}