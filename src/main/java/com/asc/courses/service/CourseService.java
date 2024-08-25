package com.asc.courses.service;

import java.util.List;

import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.exceptions.CourseNotFoundException;
import com.asc.courses.exceptions.InvalidCourseException;
import com.asc.courses.model.Course;

/**
 * Service interface for managing Course-related operations.
 * Provides methods to save, fetch, and delete courses.
 */
public interface CourseService {

    /**
     * Saves a course in the repository.
     * 
     * @param course the course entity to be saved.
     * @return the saved course entity.
     * @throws CourseExistException 
     * @throws InvalidCourseException 
     */
    Course saveCourse(Course course) throws CourseExistException, InvalidCourseException;

    /**
     * Fetches all courses from the repository.
     * 
     * @return a list of all courses.
     */
    List<Course> fetchAllCourses();

    /**
     * Fetches a course by its unique identifier.
     * 
     * @param id the unique identifier of the course.
     * @return The course if found, or empty if not found.
     * @throws CourseNotFoundException
     */
    Course fetchCourseById(Long id) throws CourseNotFoundException;

    /**
     * Fetches a course by its unique code.
     * 
     * @param code the unique course code.
     * @return The course if found, or empty if not found.
     * @throws CourseNotFoundException
     */
    Course fetchCourseByCode(String code) throws CourseNotFoundException;

    /**
     * Deletes a course by its unique identifier.
     * 
     * @param id the unique identifier of the course to be deleted.
     * @return The name of course deleted
     * @throws CourseNotFoundException
     */
    String deleteCourse(Long id) throws CourseNotFoundException;
}
