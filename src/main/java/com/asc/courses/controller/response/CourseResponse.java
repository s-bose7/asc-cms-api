package com.asc.courses.controller.response;


public class CourseResponse<T> extends ApiResponse<T> {
    
    @Deprecated
    public CourseResponse(){
        super();
    }

    public CourseResponse(int status, String message, T data){
        super(status, message, data);
    }

}
