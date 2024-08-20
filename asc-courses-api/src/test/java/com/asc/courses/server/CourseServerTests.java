package com.asc.courses.server;


import org.junit.jupiter.api.Test;
import com.asc.courses.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseServerTests {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACourceInfo() {
		ResponseEntity<Course> response = restTemplate.getForEntity(
			"/api/v1/courses/1", Course.class
		);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
	}

}
