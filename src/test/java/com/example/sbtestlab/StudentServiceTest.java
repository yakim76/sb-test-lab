package com.example.sbtestlab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class StudentServiceTest {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById() {
        //given
        Student savedStudent = studentRepo.save(new Student(null, "Fedya", true, 100));
        //when
        Student student = studentService.getStudentById(savedStudent.getId());
        //then
        then(student).isNotNull();
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Fedya");
    }
}
