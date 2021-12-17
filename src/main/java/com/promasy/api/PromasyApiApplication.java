package com.promasy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableMongoRepositories
public class PromasyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PromasyApiApplication.class, args);
	}
}
