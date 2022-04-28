package com.example.module02jwt.controller;

import com.example.module02jwt.dto.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "Xiaoting"),
            new Student(2, "Karina"),
            new Student(3, "NingNing")
    );

    @GetMapping("/{studentId}")
    public Student getStudent(
            @PathVariable("studentId") Integer studentId
    ) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student Id " + studentId + "doesn't exist"));
    }

}
