package com.example.toy1;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.web.DefaultSecurityFilterChain;

import jakarta.servlet.Filter;

// @SpringBootApplication(scanBasePackages ="com.example.toy1")
@SpringBootApplication
@EnableJpaAuditing
public class Toy1Application {

	public static void main(String[] args) {
	SpringApplication.run(Toy1Application.class, args);
	}

}
