package com.epam.training.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.epam.training")
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
