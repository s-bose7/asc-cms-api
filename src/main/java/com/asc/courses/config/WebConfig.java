package com.asc.courses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Apply CORS settings to all endpoints
        registry.addMapping("/**")  
            // Allowed origin: React App
            .allowedOrigins("http://localhost:5173")
            // Allowed HTTP Methods
            .allowedMethods("GET", "POST", "PUT", "DELETE");
        }   
}
