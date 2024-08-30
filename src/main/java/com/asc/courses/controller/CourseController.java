package com.asc.courses.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;

import com.asc.courses.common.ResponseHandler;
import com.asc.courses.controller.response.CourseResponse;
import com.asc.courses.exceptions.CourseExistException;
import com.asc.courses.exceptions.CourseNotFoundException;
import com.asc.courses.exceptions.InvalidCourseException;
import com.asc.courses.model.Course;
import com.asc.courses.service.CourseServiceImpl;



@RestController
@RequestMapping("/api/v1")
public class CourseController {
	
	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	HttpServletRequest request;

	public CourseController(CourseServiceImpl courseService){
		this.courseService = courseService;
	}

	@PostMapping("/courses")
	public ResponseEntity<CourseResponse<Course>> saveCourse(@RequestBody @Valid Course course, BindingResult result) {
		if (result.hasErrors()) {
			FieldError error = result.getFieldError();
			String message = error.getField() +": "+ error.getDefaultMessage();
			return ResponseHandler.handleInvalidCourseException(message, request);
		}
		try {
			Course savedCourse = courseService.saveCourse(course);
			return ResponseHandler.handleSuccess(savedCourse, request);
			
		} catch (CourseExistException e) {
			return ResponseHandler.handleContentExistException(e.getMessage(), request);
		} catch (InvalidCourseException e) {
			return ResponseHandler.handleInvalidCourseException(e.getMessage(), request);
		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage(), request);
		}
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<CourseResponse<Course>> getCourseById(@PathVariable Long courseId) {
		try {
			Course course = courseService.fetchCourseById(courseId);
			return ResponseHandler.handleSuccess(course, request);

		} catch (CourseNotFoundException e) {
			return ResponseHandler.handleNotFoundException(e.getMessage(), request);
		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage(), request);
		}
	}

	@GetMapping("/courses/code/{courseCode}")
	public ResponseEntity<CourseResponse<Course>> getCourseByCode(@PathVariable String courseCode) {
		try {
			Course course = courseService.fetchCourseByCode(courseCode);
			return ResponseHandler.handleSuccess(course, request);

		} catch (CourseNotFoundException e) {
			return ResponseHandler.handleNotFoundException(e.getMessage(), request);
		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage(), request);
		}
	}

	@GetMapping("/courses")
	public ResponseEntity<CourseResponse<List<Course>>> getAllCourses() {
        try {
			List<Course> courses = courseService.fetchAllCourses();
        	return ResponseHandler.handleSuccess(courses, request);
		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage(), request);
		}
    }

	@DeleteMapping("/courses/{courseId}")
    public ResponseEntity<CourseResponse<String>> deleteCourseById(@PathVariable Long courseId) {
        try {
			String courseName = courseService.deleteCourse(courseId);
			return ResponseHandler.handleSuccess(
				courseName +" has been deleted successfully", request
			);
		} catch (CourseNotFoundException e) {
			return ResponseHandler.handleNotFoundException(e.getMessage(), request);
		} catch (RuntimeException e) {
			return ResponseHandler.handleInternalServerErrorException(e.getMessage(), request);
		}
    }

}
