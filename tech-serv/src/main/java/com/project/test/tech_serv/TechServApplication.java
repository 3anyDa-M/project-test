package com.project.test.tech_serv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TechServApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechServApplication.class, args);
	}

}
