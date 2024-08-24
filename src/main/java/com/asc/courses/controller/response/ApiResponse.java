package com.asc.courses.controller.response;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    @Deprecated
    public ApiResponse(){

    }

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }    
}
