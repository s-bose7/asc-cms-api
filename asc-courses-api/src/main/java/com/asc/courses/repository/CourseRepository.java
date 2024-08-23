package com.asc.courses.repository;

import java.util.Optional;
import com.asc.courses.model.Course;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for managing Course entities.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Find a specific Course entity by the course code.
     *
     * @param code the code of the course.
     * @return an Optional containing the Course entity matching the criteria
     */
    Optional<Course> findByCourseCode(String code);
}
