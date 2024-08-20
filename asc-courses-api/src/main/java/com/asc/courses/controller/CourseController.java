package com.asc.courses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.asc.courses.model.Course;
import com.asc.courses.service.CourseService;


@RestController
@RequestMapping("/api/v1")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	public CourseController(CourseService courseService){
		this.courseService = courseService;
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
		Optional<Course> courseOptional = courseService.fetchCourseById(courseId);
		if(courseOptional.isPresent()){
			return ResponseEntity.ok(courseOptional.get());
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/courses")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		Course savedCourse = courseService.saveCourse(course);
		return ResponseEntity.ok(savedCourse);
	}

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> products = courseService.fetchAllCourses();
        return ResponseEntity.ok(products);
    }

	@DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long courseId) {
        boolean deletionStatus = courseService.deleteCourse(courseId);
        if (deletionStatus) {
            return ResponseEntity.ok(
				"Course with ID " + courseId + " has been deleted successfully"
			);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				"Failed to delete course with ID " + courseId
			);
        }
    }
}
