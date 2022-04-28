package com.example.module03authentication.controller;

import com.example.module03authentication.dto.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "John"),
            new Student(2, "Lia"),
            new Student(3, "Giselle")
    );

    @GetMapping
    public List<Student> getStudents() {
        return STUDENTS;
    }
}
