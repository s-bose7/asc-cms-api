package com.asc.courses.controller.response;

import java.util.Map;

public class CourseResponse<T> extends ApiResponse<T> {
    
    @Deprecated
    public CourseResponse(){
        super();
    }

    public CourseResponse(int status, String message, T data, Map<String, Object> metadata){
        super(status, message, data, metadata);
    }

}
