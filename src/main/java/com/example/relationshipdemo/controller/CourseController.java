package com.example.relationshipdemo.controller;

import com.example.relationshipdemo.entity.Course;
import com.example.relationshipdemo.entity.Student;
import com.example.relationshipdemo.entity.StudentCourse;
import com.example.relationshipdemo.entity.StudentCourseId;
import com.example.relationshipdemo.model.CourseModel;
import com.example.relationshipdemo.model.StudentModel;
import com.example.relationshipdemo.repository.CourseRepository;
import com.example.relationshipdemo.repository.StudentCourseRepository;
import com.example.relationshipdemo.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
@Transactional
public class CourseController {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public CourseController(CourseRepository courseRepository, StudentRepository studentRepository,
                            StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @PostMapping("create")
    public ResponseEntity<CourseModel> create(@RequestBody CourseModel request) {
        Course course = courseRepository.save(
                Course.builder()
                        .name(request.getName())
                        .build()
        );

        List<Student> students = studentRepository.findAllById(request.getStudentIds());
        Set<StudentCourse> studentCourses = new HashSet<>();
        int ordinal = 1;
        for (Student student : students) {
            StudentCourse studentCourse = studentCourseRepository.save(
                    StudentCourse.builder()
                            .id(
                                    StudentCourseId.builder()
                                            .studentId(student.getId())
                                            .courseId(course.getId())
                                            .build()
                            )
                            .ordinal(ordinal)
                            .course(course)
                            .student(student)
                            .build()
            );
            studentCourses.add(studentCourse);
            ordinal++;
        }
        studentCourseRepository.saveAll(studentCourses);

        return ResponseEntity.ok(
                CourseModel.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .students(
                                studentCourses.stream().map(
                                        entity -> StudentModel.builder()
                                                .id(entity.getStudent().getId())
                                                .ordinal(entity.getOrdinal())
                                                .name(entity.getStudent().getName())
                                                .build()
                                ).collect(Collectors.toSet())
                        )
                        .build()
        );
    }

    @GetMapping("{id}/detail")
    public ResponseEntity<Object> getDetail(@PathVariable UUID id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("VL");
        }

        Course course = courseOptional.get();
        return ResponseEntity.ok(
                CourseModel.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .students(
                                course.getStudentCourses().stream().map(
                                        entity -> StudentModel.builder()
                                                .id(entity.getStudent().getId())
                                                .ordinal(entity.getOrdinal())
                                                .name(entity.getStudent().getName())
                                                .build()
                                ).collect(Collectors.toSet())
                        )
                        .build()
        );
    }
}
