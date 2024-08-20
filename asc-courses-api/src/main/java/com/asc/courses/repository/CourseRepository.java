package com.asc.courses.repository;

import com.asc.courses.model.Course;
import org.springframework.stereotype.Repository; 
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
