package com.asc.courses.service;

import java.util.List;
import java.util.Optional;

import com.asc.courses.exceptions.CourseExistException;
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
     * @throws CourseExistException if a course with the same code already exists.
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
     * @return an `Optional` containing the course if found, or empty if not found.
     */
    Optional<Course> fetchCourseById(Long id);

    /**
     * Fetches a course by its unique code.
     * 
     * @param code the unique course code.
     * @return an `Optional` containing the course if found, or empty if not found.
     */
    Optional<Course> fetchCourseByCode(String code);

    /**
     * Deletes a course by its unique identifier.
     * 
     * @param id the unique identifier of the course to be deleted.
     * @return `true` if the course was successfully deleted, `false` otherwise.
     */
    boolean deleteCourse(Long id);
}
