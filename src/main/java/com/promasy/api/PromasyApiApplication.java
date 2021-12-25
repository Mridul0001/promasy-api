package com.promasy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Security not implemented yet. Busy with interview preps :)*/
@SpringBootApplication
@EnableMongoRepositories
public class PromasyApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PromasyApiApplication.class, args);
	}

	//Adding CORS handling method, at present it accepts all origins for GET,POST,PUT,DELETE
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("https://promasy-ui.herokuapp.com");
			}
		};
	}
}
