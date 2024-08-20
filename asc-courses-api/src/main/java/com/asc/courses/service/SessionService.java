package com.asc.courses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asc.courses.model.CourseSession;
import com.asc.courses.repository.SessionRepository;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public CourseSession saveSession(CourseSession courseSession){
        try {
            return sessionRepository.save(courseSession);
        } catch (Exception e){
            throw new RuntimeException("Failed to save course delivery: " + e.getMessage());
        }
    }

    public Optional<List<CourseSession>> fetchCourseSessionsByYearAndSemester(int year, int semester){
        try {
            return sessionRepository.findByYearAndSemester(year, semester);
        } catch (Exception e){
            throw new RuntimeException("Failed to fetch course delivery by Year and Semester:" + e.getMessage());
        }
    }

    public Optional<CourseSession> fetchCourseSessionByYearSemesterAndCourseId(int year, int semester, int id){
        try {
            return sessionRepository.findByYearAndSemesterAndCourseId(year, semester, id);
        } catch (Exception e){
            throw new RuntimeException("Failed to fetch course delivery by Year, Semester and Id:" + e.getMessage());
        }
    }

    @Transactional
    public boolean deleteCourseSession(int year, int semester, int id) {
        try {
            sessionRepository.deleteByYearAndSemesterAndId(year, semester, id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete course delivery: " + e.getMessage());
        }
    }
}
