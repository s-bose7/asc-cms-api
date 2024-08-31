package com.asc.courses.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

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

    @Test
    @DisplayName("Test 1: Save Course Test")
    @Order(1)
    void saveCourseTest() {
        Course course1 = Course.builder()
            .courseTitle("Introduction to Compiler Design")
            .courseCode("CS 61A")
            .courseDescription("A concise introduction to compilers and their design")
            .build();
        
        Course savedCourse1 = courseRepository.save(course1);

        // Assertions
        assertThat(savedCourse1).isNotNull();
        assertThat(savedCourse1.getClass()).isEqualTo(Course.class);
        assertThat(savedCourse1.getId()).isGreaterThan(0);
        assertThat(savedCourse1.getCourseCode()).isEqualTo(course1.getCourseCode());
        assertThat(savedCourse1.getCourseTitle()).isEqualTo(course1.getCourseTitle());
        assertThat(savedCourse1.getCourseDescription()).isEqualTo(course1.getCourseDescription());


        Course course2 = Course.builder()
            .courseTitle("Structure and Interpretation of Computer Programs")
            .courseCode("CS 51B")
            .courseDescription("Fundamental concepts of computer programming")
            .build();
        
        Course savedCourse2 = courseRepository.save(course2);

        // Assertions
        assertThat(savedCourse2).isNotNull();
        assertThat(savedCourse2.getClass()).isEqualTo(Course.class);
        assertThat(savedCourse2.getId()).isGreaterThan(0);
        assertThat(savedCourse2.getCourseCode()).isEqualTo(course2.getCourseCode());
        assertThat(savedCourse2.getCourseTitle()).isEqualTo(course2.getCourseTitle());
        assertThat(savedCourse2.getCourseDescription()).isEqualTo(course2.getCourseDescription());

    }

    @Test
    @DisplayName("Test 2: Get All Courses Test")
    @Order(2)
    void findAllCourseTest() {
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList.size()).isEqualTo(21);

        // Assertions
        for (Course course : courseList) {
            assertThat(course).isNotNull();
            assertThat(course.getCreatedAt().getClass()).isEqualTo(Date.class);
            // Making sure no Leading/Trailing white-spaces in course code
            // REFERENCE: https://github.com/s-bose7/asc-cms-api/issues/21
            assertThat(course.getCourseCode().length()).isEqualTo(course.getCourseCode().strip().length());
        }
    }


    @Test
    @DisplayName("Test 3: Get Course By CODE Test")
    @Order(3)
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
    @DisplayName("Test 4: Get Course By ID Test")
    @Order(4)
    void findCourseByIdTest() {
        Course course1 = courseRepository.findByCourseCode("CS 301")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 301' not found"));
        assertThat(course1.getId()).isGreaterThan(0);
        
        Course course2 = courseRepository.findById(course1.getId())
            .orElseThrow(()-> new NoSuchElementException("Course with ID "+course1.getId()+" not found"));

        //Assertions
        assertThat(course2.getCourseCode()).isEqualTo(course1.getCourseCode());
        assertThat(course2.getCourseTitle()).isEqualTo(course1.getCourseTitle());
        assertThat(course2.getCourseDescription())
            .isEqualTo(course1.getCourseDescription());
        
        Course course3 = courseRepository.findByCourseCode("CS 1009")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 1009' not found"));
        assertThat(course3.getId()).isGreaterThan(0);

        Course course4 = courseRepository.findById(course3.getId())
            .orElseThrow(() -> new NoSuchElementException("Course with ID "+course3.getId()+" not found"));

        //Assertions
        assertThat(course4.getCourseCode()).isEqualTo(course3.getCourseCode());
        assertThat(course4.getCourseTitle()).isEqualTo(course3.getCourseTitle());
        assertThat(course4.getCourseDescription())
            .isEqualTo(course3.getCourseDescription());

    }

    @Test
    @DisplayName("Test 5: Delete Course By ID Test")
    @Order(5)
    void deleteCourseByIdTest() {
        Course course1 = courseRepository.findByCourseCode("CS 301")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 301' not found"));
        assertThat(course1.getId()).isGreaterThan(0);
        courseRepository.deleteById(course1.getId());
        
        // Assertions
        Optional<Course> course2 = courseRepository.findById(course1.getId());
        assertThat(course2.isPresent()).isFalse();

        Course course3 = courseRepository.findByCourseCode("CS 1009")
            .orElseThrow(()-> new NoSuchElementException("Course with CODE 'CS 1009' not found"));
        assertThat(course3.getId()).isGreaterThan(0);
        courseRepository.deleteById(course3.getId());
        
        // Assertions
        Optional<Course> course4 = courseRepository.findById(course3.getId());
        assertThat(course4.isPresent()).isFalse();      
    }
}
