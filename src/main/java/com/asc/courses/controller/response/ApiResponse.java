package com.asc.courses.controller.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Data
@JsonPropertyOrder({ "apiVersion", "status", "message", "timestamp", "traceId", "metadata", "data" })
public class ApiResponse<T> {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private T data;
    private String apiVersion;
    private String traceId;
    private Map<String, Object> metadata;

    @Deprecated
    public ApiResponse(){

    }

    public ApiResponse(
        Integer status, String message, T data, Map<String, Object> metadata
    ) {
        this.apiVersion = "1.0";
        this.traceId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.metadata = metadata;
        this.data = data;
        
    }    
}
