package com.example.sbtestlab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student getStudentByName(String mark);

    @Query("SELECT AVG(grade) FROM Student where active = true")
    Double getAvgGrade();
}
