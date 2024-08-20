package com.asc.courses.repository;

import java.util.List;
import java.util.Optional;

import com.asc.courses.model.CourseSession;

import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing CourseSession entities.
 */
@Repository
public interface SessionRepository extends JpaRepository<CourseSession, Long> {
    
    /**
     * Find a list of CourseSession entities by the year of delivery and semester.
     *
     * @param year the year in which the course session is delivered.
     * @param semester the semester in which the course session is delivered.
     * @return an Optional containing a list of CourseSession entities matching the criteria.
     */
    Optional<List<CourseSession>> findByYearAndSemester(int year, int semester);

    /**
     * Find a specific CourseSession entity by the year of delivery, semester, and course ID.
     *
     * @param year the year in which the course session is delivered.
     * @param semester the semester in which the course session is delivered.
     * @param id the ID of the course.
     * @return an Optional containing the CourseSession entity matching the criteria, or empty if not found.
     */
    Optional<CourseSession> findByYearAndSemesterAndCourseId(int year, int semester, int id);

    /**
     * Delete a CourseSession entity by the year of delivery, semester, and ID.
     *
     * @param year the year in which the course session is delivered.
     * @param semester the semester in which the course session is delivered.
     * @param id the ID of the course to be deleted.
     */
    void deleteByYearAndSemesterAndId(int year, int semester, int id);
}
