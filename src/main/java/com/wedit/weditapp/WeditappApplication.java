package com.wedit.weditapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WeditappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeditappApplication.class, args);
	}

}
