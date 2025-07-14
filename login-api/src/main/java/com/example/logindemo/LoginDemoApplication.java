package com.example.logindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.logindemo")
public class LoginDemoApplication {

	public static void main(String[] args) {
		System.out.println("Starting Spring Boot application...");
		SpringApplication application = new SpringApplication(LoginDemoApplication.class);
		application.setAddCommandLineProperties(true);
		application.run(args);
		System.out.println("Spring Boot application started.");
	}

}
