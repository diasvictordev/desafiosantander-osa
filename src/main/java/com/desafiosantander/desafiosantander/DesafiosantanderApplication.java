package com.desafiosantander.desafiosantander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesafiosantanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiosantanderApplication.class, args);
	}

}
