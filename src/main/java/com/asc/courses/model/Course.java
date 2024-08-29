package com.asc.courses.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Temporal;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
