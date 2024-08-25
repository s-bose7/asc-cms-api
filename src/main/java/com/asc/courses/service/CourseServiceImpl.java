package com.asc.courses.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.Course;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.exceptions.CourseNotFoundException;
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
            Optional<Course> savedCourse = courseRepository.findByCourseCode(course.getCourseCode());
            if(savedCourse.isPresent()){
                throw new CourseExistException("Course already present with code: "+course.getCourseCode());
            }
            return courseRepository.save(course);

        } catch (CourseExistException courseExistException){
            throw courseExistException;
        } catch (Exception e) { 
            throw new RuntimeException("Failed to save course: " + e.getMessage());
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
    public Course fetchCourseById(Long id) throws CourseNotFoundException {
        try {
            Optional<Course> courseOptional = courseRepository.findById(id);
            if(courseOptional.isPresent()){
                return courseOptional.get();
            } else {
                throw new CourseNotFoundException("Course with id "+id+" not found");
            }
        } catch (CourseNotFoundException courseNotFoundException){
            throw courseNotFoundException;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch course by id: " + e.getMessage());
        }
    }


    @Override
    public Course fetchCourseByCode(String code) throws CourseNotFoundException {
        try {
            Optional<Course> courseOptional = courseRepository.findByCourseCode(code);
            if(courseOptional.isPresent()){
                return courseOptional.get();
            } else {
                throw new CourseNotFoundException("Course with code "+code+" not found");
            }
        } catch (CourseNotFoundException courseNotFoundException){
            throw courseNotFoundException;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch course by code: " + e.getMessage());
        }
    }


    @Override
    @Transactional
    public String deleteCourse(Long id) throws CourseNotFoundException {
        try {
            Optional<Course> courseOptional = courseRepository.findById(id);
            if (courseOptional.isPresent()){
                String courseName = courseOptional.get().getCourseTitle();
                courseRepository.deleteById(id);
                return courseName;
            } else {
                throw new CourseNotFoundException("Course with ID "+id+" not found");
            }
        } catch (CourseNotFoundException courseNotFoundException){
            throw courseNotFoundException;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete course: " + e.getMessage());
        }
    }
}
