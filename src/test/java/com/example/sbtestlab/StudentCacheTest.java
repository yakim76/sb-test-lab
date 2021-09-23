package com.example.sbtestlab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = NONE)
@EnableCaching
class StudentCacheTest {
    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepo studentRepo;

    @Test
    void getStudentById() {
        //given
        Long id = 777L;
        given(studentRepo.findById(id)).willReturn(Optional.of(new Student(id, "Fedya", true, 100)));
        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        //then
        then(studentRepo).should(times(1)).findById(id);
    }
}
