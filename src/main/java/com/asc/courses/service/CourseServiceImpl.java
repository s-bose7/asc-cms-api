package com.asc.courses.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.exceptions.InvalidCourseException;
import com.asc.courses.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    private boolean isNotValidCourseCode(String code) {
        // Regex to match any character that is NOT a letter (a-z, A-Z),
        // a digit (0-9), a period (.), or a space (\u0020). 
        String regex = "[^a-zA-Z0-9. ]";
        Matcher matcher = Pattern.compile(regex).matcher(code);
        // returns true if the course code contains any character 
        // that matches the regex (i.e., any invalid character)
        return matcher.find();
    }

    @Override
    public Course saveCourse(Course course) throws CourseExistException, InvalidCourseException {
        if(isNotValidCourseCode(course.getCourseCode())){
            throw new InvalidCourseException("Course code is not valid: "+course.getCourseCode());
        }
        try {
            Optional<Course> savedCourse = fetchCourseByCode(course.getCourseCode());
            if(savedCourse.isPresent()){
                throw new CourseExistException("Course already present with code: "+course.getCourseCode());
            }
            return courseRepository.save(course);

        } catch (RuntimeException e) { 
            throw new RuntimeException("Failed to save course: " + course.getCourseTitle());
        }
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
