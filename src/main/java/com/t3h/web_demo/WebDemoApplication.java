package com.t3h.web_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDemoApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<String> root() {
		return ResponseEntity.ok("Vào thành công đường dẫn root /");
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("hellol");
	}

}
