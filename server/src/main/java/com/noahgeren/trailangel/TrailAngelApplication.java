package com.noahgeren.trailangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrailAngelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailAngelApplication.class, args);
	}

}
