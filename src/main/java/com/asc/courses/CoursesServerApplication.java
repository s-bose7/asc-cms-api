package com.asc.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoursesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesServerApplication.class, args);
	}

}
