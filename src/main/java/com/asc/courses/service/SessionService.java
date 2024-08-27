package com.asc.courses.service;

import java.util.List;
import java.util.Optional;

import com.asc.courses.exceptions.SessionExistException;
import com.asc.courses.model.CourseSession;

/**
 * Service interface for managing course sessions.
 */
public interface SessionService {

    /**
     * Saves a new course session if it doesn't already exist.
     *
     * @param courseSession the course session to be saved
     * @return the saved course session
     * @throws SessionExistException if a session for the specified year, semester, and course ID already exists
     */
    CourseSession saveSession(CourseSession courseSession) throws SessionExistException;

    /**
     * Fetches a list of course sessions based on the specified year and semester.
     *
     * @param year the year of the course session
     * @param semester the semester of the course session
     * @return an Optional containing the list of course sessions, or an empty Optional if none are found
     */
    Optional<List<CourseSession>> fetchCourseSessionsByYearAndSemester(int year, int semester);

    /**
     * Fetches a specific course session based on the specified year, semester, and course ID.
     *
     * @param year the year of the course session
     * @param semester the semester of the course session
     * @param id the course ID
     * @return an Optional containing the course session, or an empty Optional if not found
     */
    Optional<CourseSession> fetchCourseSessionByYearSemesterAndCourseId(int year, int semester, int id);

    /**
     * Deletes a course session based on the specified year, semester, and course ID.
     *
     * @param year the year of the course session
     * @param semester the semester of the course session
     * @param id the course ID
     * @return true if the session was successfully deleted, false otherwise
     */
    boolean deleteCourseSession(int year, int semester, int id);
}
