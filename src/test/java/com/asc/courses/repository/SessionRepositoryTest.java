package com.asc.courses.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "/data.sql")
public class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        // Clear the session table
        sessionRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Course Session Test")
    void saveCourseSessionTest() {

    }

}
