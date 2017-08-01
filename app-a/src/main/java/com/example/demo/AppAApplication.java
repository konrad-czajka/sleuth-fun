package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class AppAApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppAApplication.class, args);
	}
}

@RestController
class AppAController {
	@Autowired private Service2 service2;

	@GetMapping("/start")
	List<String> start(@RequestParam String data) {
		return service2.prepareData(data);
	}
}

@Service
@Slf4j
class Service1 {
	int countCharacters(String data) {
		log.info("Counting characters of data=" + data);
		return data.length();
	}
}

@Service
@Slf4j
class Service2 {
	@Autowired private Service1 service1;

	List<String> prepareData(String data) {
		int length = service1.countCharacters(data);
		log.info("Preparing data for length=" + length);
		return IntStream.range(1, length+1)
				.mapToObj(v -> data.substring(0, v))
				.collect(Collectors.toList());
	}
}