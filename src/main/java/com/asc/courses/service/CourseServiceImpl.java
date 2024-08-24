package com.asc.courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }


    @Override
    public Course saveCourse(Course course) throws CourseExistException {
        Course newCourse;
        try {
            Optional<Course> savedCourse = fetchCourseByCode(course.getCourseCode());
            if(savedCourse.isPresent()){
                throw new CourseExistException("Course already present with code: "+course.getCourseCode());
            }
            newCourse = courseRepository.save(course);

        } catch (RuntimeException e) { 
            throw new RuntimeException("Failed to save course: " + course.getCourseTitle());
        }
        return newCourse;
    }


    @Override
    public List<Course> fetchAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all courses: " + e.getMessage());
        }
    }


    @Override
    public Optional<Course> fetchCourseById(Long id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch course by ID: " + e.getMessage());
        }
    }


    @Override
    public Optional<Course> fetchCourseByCode(String code) {
        try {
            return courseRepository.findByCourseCode(code);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch course by code: " + e.getMessage());
        }
    }


    @Override
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
