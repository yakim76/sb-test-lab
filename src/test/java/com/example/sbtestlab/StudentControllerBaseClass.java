package com.example.sbtestlab;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class StudentControllerBaseClass {
    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void before() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
        given(studentService.getStudentById(anyLong())).willReturn(new Student(1L, "Fedya", true, 10));

    }
}
