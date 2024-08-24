package com.asc.courses.server;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CourseServerTests {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldStartTheServer() {
		assertThat(1).isEqualTo(1);
	}

}
