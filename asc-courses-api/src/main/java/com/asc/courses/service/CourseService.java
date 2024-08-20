package com.asc.courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;
import com.asc.courses.repository.CourseRepository;


@Service
public class CourseService {
    
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course){
        try {
            return courseRepository.save(course);
        } catch (Exception e){
            throw new RuntimeException("Failed to save course: " + e.getMessage());
        }
    }

    public List<Course> fetchAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all courses: " + e.getMessage());
        }
    }

    public Optional<Course> fetchCourseById(Long id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch course by ID: " + e.getMessage());
        }
    }

    @Transactional
    public boolean deleteCourse(Long id) {
        try {
            courseRepository.deleteById(id);
            // On successfull deletion
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete course: " + e.getMessage());
        }
    }
}
