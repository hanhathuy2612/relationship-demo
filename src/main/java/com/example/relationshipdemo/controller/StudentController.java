package com.example.relationshipdemo.controller;

import com.example.relationshipdemo.entity.Student;
import com.example.relationshipdemo.model.StudentModel;
import com.example.relationshipdemo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class StudentController {
    public final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("create")
    public ResponseEntity<StudentModel> create(@RequestBody StudentModel request) {
        Student student = studentRepository.save(
                Student.builder()
                        .name(request.getName())
                        .build()
        );
        return ResponseEntity.ok(StudentModel.builder()
                .id(student.getId())
                .name(student.getName())
                .build());
    }
}
