package com.example.module04formlogin.controller;

import com.example.module04formlogin.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "Xiaoting"),
            new Student(2, "Karina"),
            new Student(3, "NingNing")
    );

    @GetMapping
    public List<Student> getStudents() {
        return STUDENTS;
    }
}
