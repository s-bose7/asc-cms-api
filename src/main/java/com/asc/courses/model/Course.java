package com.asc.courses.model;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;



@Entity
@Table(name = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 60, min = 10)
    @Column(name = "course_title", nullable = false)
    private String courseTitle;
    
    @NotBlank
    @Size(max = 10, min = 5)
    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @NotBlank
    @Size(max = 300)
    @Column(name = "course_description")
    private String courseDescription;

}
