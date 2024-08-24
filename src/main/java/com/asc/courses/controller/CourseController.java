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

import com.asc.courses.controller.response.CourseResponse;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.model.Course;
import com.asc.courses.service.CourseServiceImpl;


@RestController
@RequestMapping("/api/v1")
public class CourseController {
	
	@Autowired
	private CourseServiceImpl courseService;

	public CourseController(CourseServiceImpl courseService){
		this.courseService = courseService;
	}

	@PostMapping("/courses")
	public ResponseEntity<CourseResponse<Course>> saveCourse(@RequestBody Course course) {
		// Validate RequestBody before calling service.
		CourseResponse<Course> response;
		try {
			Course savedCourse = courseService.saveCourse(course);
			response = new CourseResponse<Course>(
				200, "Success", savedCourse
			);
			return ResponseEntity.ok(response);

		} catch (CourseExistException e) {
			response = new CourseResponse<Course>(
				HttpStatus.CONFLICT.value(), e.getMessage(), null
			);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		
		} catch (RuntimeException e) {
			response = new CourseResponse<Course>(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null
			);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
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

	@GetMapping("/courses/code/{courseCode}")
	public ResponseEntity<Course> getCourseByCode(@PathVariable String courseCode) {
		Optional<Course> courseOptional = courseService.fetchCourseByCode(courseCode);
		if(courseOptional.isPresent()){
			return ResponseEntity.ok(courseOptional.get());
		}else{
			return ResponseEntity.notFound().build();
		}
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
