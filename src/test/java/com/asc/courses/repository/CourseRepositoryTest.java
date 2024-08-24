package com.asc.courses.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    @DisplayName("Test 1: Save Employee Test")
    @Order(1)
    public void saveCourseTest(){
        
        Course course = Course.builder()
            .courseTitle("Introduction to Computer Programming")
            .courseCode("6.0006")
            .courseDescription("A concise introduction to the art of programming.")
            .build();

        courseRepository.save(course);
        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.getClass()).isEqualTo(Course.class);
        Assertions.assertThat(course.getId()).isGreaterThan(0);
    }
    
}
