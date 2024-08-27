package com.asc.courses.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.asc.courses.common.ResponseHandler;
import com.asc.courses.controller.response.CourseResponse;
import com.asc.courses.exceptions.SessionExistException;
import com.asc.courses.model.CourseSession;
import com.asc.courses.service.SessionServiceImpl;


@RestController
@RequestMapping("/api/v1")
public class SessionController {

    @Autowired
	private SessionServiceImpl sessionService;

    public SessionController(SessionServiceImpl sessionService) {
		this.sessionService = sessionService;
	}

    @PostMapping("/instances")
    public ResponseEntity<CourseResponse<CourseSession>> saveCourseSession(@RequestBody CourseSession courseSession) {
        try {
            CourseSession session = sessionService.saveSession(courseSession);
            return ResponseHandler.handleSuccess(session, "Success");

        } catch (SessionExistException e) {
            return ResponseHandler.handleContentExistException(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseHandler.handleInternalServerErrorException(e.getMessage());
        } 
        
    }

    @GetMapping("/instances/{year}/{semester}")
    public ResponseEntity<CourseResponse<List<CourseSession>>> getAllCourseSessionByYearAndSemester(
        @PathVariable("year") int year, @PathVariable("semester") int semester) {
        try{
            Optional<List<CourseSession>> sessionsOptional = sessionService.fetchCourseSessionsByYearAndSemester(year, semester);
            if(sessionsOptional.isPresent()){
                return ResponseHandler.handleSuccess(sessionsOptional.get(), "Success");
            }
            return ResponseHandler.handleNotFoundException("No Sessions Found");
        } catch (RuntimeException e) {
            return ResponseHandler.handleInternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/instances/{year}/{semester}/{id}")
    public ResponseEntity<CourseResponse<CourseSession>> getAllCourseSessionByYearAndSemesterAndId(
        @PathVariable("year") int year, @PathVariable("semester") int semester, @PathVariable("id") int id) {
        try {
            Optional<CourseSession> sessionOptional = sessionService.fetchCourseSessionByYearSemesterAndCourseId(year, semester, id);
            if (sessionOptional.isPresent()) {
                return ResponseHandler.handleSuccess(sessionOptional.get(), "Success");
            }
            return ResponseHandler.handleNotFoundException("No Sessions Found");
        } catch (RuntimeException e){
            return ResponseHandler.handleInternalServerErrorException(e.getMessage());
        }
        
    }

    @DeleteMapping("/instances/{year}/{semester}/{id}")
    public ResponseEntity<CourseResponse<String>> deleteCourseSessionByYearAndSemesterAndId(
        @PathVariable("year") int year, @PathVariable("semester") int semester, @PathVariable("id") int id) {
        try{
            boolean deletionStatus = sessionService.deleteCourseSession(year, semester, id);
            if (deletionStatus) {
                return ResponseHandler.handleSuccess(
                    "Course with ID " + id + " has been deleted from sessions successfully", "Success"
                );
            }
            return ResponseHandler.handleNotFoundException("No Course with ID " + id + " found for deletion");
        } catch (RuntimeException e){
            return ResponseHandler.handleInternalServerErrorException(e.getMessage());
        }
    }
}
