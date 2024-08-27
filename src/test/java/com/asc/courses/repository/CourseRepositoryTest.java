package com.asc.courses.repository;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "/data.sql")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    private final int DB_SIZE = 21;

    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        courseRepository.deleteAll();
    }


    @Test
    @DisplayName("Test 1: Save Course Test")
    @Order(1)
    void saveCourseTest() {
        Course course = Course.builder()
            .courseTitle("Introduction to Compiler Design")
            .courseCode("CS 61A")
            .courseDescription("A concise introduction to compilers and their design")
            .build();
        
        Course savedCourse = courseRepository.save(course);

        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getId()).isGreaterThan(0);
        assertThat(savedCourse.getCourseCode()).isEqualTo("CS 61A");
        assertThat(savedCourse.getCourseTitle()).isEqualTo("Introduction to Compiler Design");
        assertThat(course.getCourseDescription())
            .isEqualTo("A concise introduction to compilers and their design");

    }

    @Test
    @DisplayName("Test 2: Get All Courses Test")
    @Order(2)
    void findAllCourseTest() {
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList.size()).isEqualTo(DB_SIZE);
        Course coursePick = null;
        for (Course course : courseList) {
            if(course.getCourseCode().equals("CS 1999")) {
                coursePick = course;
                break;
            }
        }
        assertThat(coursePick.getId()).isGreaterThan(0);
        assertThat(coursePick.getCourseTitle()).isEqualTo("Artificial Intelligence");
        assertThat(coursePick.getCourseDescription())
            .isEqualTo("An overview of AI, including machine learning, neural networks, and robotics");
    }

    @Test
    @DisplayName("Test 3: Get Course By ID Test")
    @Order(3)
    void findCourseByIdTest() {

    }

    @Test
    @DisplayName("Test 4: Get Course By CODE Test")
    @Order(4)
    void findCourseByCodeTest() {
        Course course1 = courseRepository.findByCourseCode("CS 301")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 301' not found"));

        // Assertions
        assertThat(course1.getCourseCode()).isEqualTo("CS 301");
        assertThat(course1.getCourseTitle()).isEqualTo("Operating Systems");
        assertThat(course1.getCourseDescription())
            .isEqualTo("Essentials of operating systems, focusing on process management and memory management");

        
        Course course2 = courseRepository.findByCourseCode("CS 1009")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 1009' not found"));

        // Assertions
        assertThat(course2.getCourseCode()).isEqualTo("CS 1009");
        assertThat(course2.getCourseTitle()).isEqualTo("Blockchain Technology");
        assertThat(course2.getCourseDescription())
            .isEqualTo("An introduction to blockchain and distributed ledger technologies");
    }

    @Test
    @DisplayName("Test 5: Delete Course By ID Test")
    @Order(5)
    void deleteCourseByIdTest(){

    }
}
