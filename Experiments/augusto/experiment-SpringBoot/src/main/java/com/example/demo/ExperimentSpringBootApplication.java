package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ExperimentSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExperimentSpringBootApplication.class, args);
	}

}

@RestController 
class HelloGbController { 
@GetMapping 
   public String helloGb() { 
   return "Gaurav Bytes says, \"Hello There!!!\""; 
   } 
}