package com.asc.courses.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.asc.courses.model.CourseSession;
import com.asc.courses.service.SessionService;


@RestController
@RequestMapping("/api/v1")
public class SessionController {

    @Autowired
	private SessionService sessionService;

    public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}

    @PostMapping("/instances")
    public ResponseEntity<CourseSession> saveCourseSession(@RequestBody CourseSession courseSession) {
        CourseSession session = sessionService.saveSession(courseSession);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/instances/{year}/{semester}")
    public ResponseEntity<List<CourseSession>> getAllCourseSessionByYearAndSemester(
        @PathVariable("year") int year, @PathVariable("semester") int semester) {
        Optional<List<CourseSession>> sessionsOptional = sessionService.fetchCourseSessionsByYearAndSemester(year, semester);
        if(sessionsOptional.isPresent()){
            return ResponseEntity.ok(sessionsOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/instances/{year}/{semester}/{id}")
    public ResponseEntity<CourseSession> getAllCourseSessionByYearAndSemesterAndId(
        @PathVariable("year") int year, @PathVariable("semester") int semester, @PathVariable("id") int id) {
        Optional<CourseSession> sessionOptional = sessionService.fetchCourseSessionByYearSemesterAndCourseId(year, semester, id);
        if (sessionOptional.isPresent()) {
            return ResponseEntity.ok(sessionOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/instances/{year}/{semester}/{id}")
    public ResponseEntity<String> deleteCourseSessionByYearAndSemesterAndId(
        @PathVariable("year") int year, @PathVariable("semester") int semester, @PathVariable("id") int id) {
        boolean deletionStatus = sessionService.deleteCourseSession(year, semester, id);
        if (deletionStatus) {
            return ResponseEntity.ok(
				"Course with ID " + id + " has been deleted from sessions successfully"
			);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				"Failed to delete course with ID " + id
			);
        }
    }
}
