package com.asc.courses.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.asc.courses.controller.response.CourseResponse;

public class ResponseHandler {

    public static <T> ResponseEntity<CourseResponse<T>> handleSuccess(T data, String message) {
        CourseResponse<T> response = new CourseResponse<>(
            HttpStatus.OK.value(), message, data
        );
        return ResponseEntity.ok(response);
    }


    public static <T> ResponseEntity<CourseResponse<T>> handleInvalidCourseException(String message){
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.BAD_REQUEST.value(), message, null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    public static <T> ResponseEntity<CourseResponse<T>> handleCourseExistException(String message){
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.CONFLICT.value(), message, null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    
    public static <T> ResponseEntity<CourseResponse<T>> handleInternalServerErrorException(String message){
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<CourseResponse<T>> handleNotFoundException(String message){
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.NOT_FOUND.value(), message, null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
