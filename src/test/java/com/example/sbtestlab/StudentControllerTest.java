package com.example.sbtestlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @Test
    void getStudentForExisted() throws Exception {
        given(studentService.getStudentById(anyLong())).willReturn(new Student(1L, "Fedya", true, 10));
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Fedya"))
                .andExpect(jsonPath("grade").value(10));

    }

    @Test
    void getStudentForMissed() throws Exception{
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
