package com.asc.courses.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.asc.courses.controller.response.CourseResponse;
import static com.asc.courses.constants.GlobalConstants.*;


public class ResponseHandler {

    private static Map<String, Object> getRequestMetaData(HttpServletRequest request) {
        Map<String, Object> metadataMapping = new HashMap<>();
        // Populate request specific info
        metadataMapping.put("requestID", request.getRequestId());
        metadataMapping.put("endpoint", request.getRequestURI());
        metadataMapping.put("requestURL", request.getRequestURL().toString());
        // Return metadata specific to endpoint
        return metadataMapping;
    }

    public static <T> ResponseEntity<CourseResponse<T>> handleSuccess(T data, HttpServletRequest request) {
        Map<String, Object> metadata = getRequestMetaData(request);
        if (data instanceof List<?>) {
            List<?> list = (List<?>) data;
            metadata.put("totalResults", list.size());
        }
        CourseResponse<T> response = new CourseResponse<>(
            HttpStatus.OK.value(), "Success", data, metadata
        );
        return ResponseEntity.ok(response);
    }


    public static <T> ResponseEntity<CourseResponse<T>> handleInvalidCourseException(String message, HttpServletRequest request){
        Map<String, Object> metadata = getRequestMetaData(request);
        metadata.put("Error Code", ERR_BAD_REQUEST);
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.BAD_REQUEST.value(), message, null, metadata
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    public static <T> ResponseEntity<CourseResponse<T>> handleContentExistException(String message, HttpServletRequest request){
        Map<String, Object> metadata = getRequestMetaData(request);
        metadata.put("Error Code", ERR_CONFLICT);
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.CONFLICT.value(), message, null, metadata
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    
    public static <T> ResponseEntity<CourseResponse<T>> handleInternalServerErrorException(String message, HttpServletRequest request){
        Map<String, Object> metadata = getRequestMetaData(request);
        metadata.put("Error Code", ERR_INTERNAL_SERVER);
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null, metadata
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<CourseResponse<T>> handleNotFoundException(String message, HttpServletRequest request){
        Map<String, Object> metadata = getRequestMetaData(request);
        metadata.put("Error Code", ERR_NOT_FOUND);
        CourseResponse<T> response = new CourseResponse<T>(
            HttpStatus.NOT_FOUND.value(), message, null, metadata
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
