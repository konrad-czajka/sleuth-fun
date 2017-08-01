package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppBApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppBApplication.class, args);
	}
}

@RestController
@Slf4j
class AppBController {

	@GetMapping("/count")
	int count(@RequestParam String word) {
		log.info("Counting letters of word=" + word);
		return word.length();
	}
}