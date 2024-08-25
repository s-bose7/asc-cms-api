package com.asc.courses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.asc.courses.common.ResponseHandler;
import com.asc.courses.controller.response.CourseResponse;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.exceptions.InvalidCourseException;
import com.asc.courses.model.Course;
import com.asc.courses.service.CourseServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class CourseController {
	
	@Autowired
	private CourseServiceImpl courseService;

	public CourseController(CourseServiceImpl courseService){
		this.courseService = courseService;
	}

	@PostMapping("/courses")
	public ResponseEntity<CourseResponse<Course>> saveCourse(
		@RequestBody @Valid Course course, BindingResult result
	) {
		if (result.hasErrors()) {
			String message = result.getFieldError().getDefaultMessage();
			return ResponseHandler.handleInvalidCourseException(message);

		}try {
			Course savedCourse = courseService.saveCourse(course);
			return ResponseHandler.handleSuccess(savedCourse, "Success");

		} catch (CourseExistException e) {
			return ResponseHandler.handleCourseExistException(e.getMessage());

		} catch (InvalidCourseException e) {
			return ResponseHandler.handleInvalidCourseException(e.getMessage());

		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage());
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
