package com.example.sbtestlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName() {
        //given
        Student savedStudent = testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Mark", false, 10)));
        testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Jason", true, 15)));
        testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Jason", true, 100)));
        //when
        Student student = studentRepo.getStudentByName("Mark");
        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void getAvgGrade() {
        //given
        Student savedStudent = testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Mark", false, 10)));
        testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Jason", true, 15)));
        testEntityManager.persistFlushFind(studentRepo.save(new Student(null, "Jason", true, 100)));
        //when
        Double avg = studentRepo.getAvgGrade();
        //then
        then(avg).isNotNull();
        then(avg).isEqualTo(57.5);

    }
}
