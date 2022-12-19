package com.example.relationshipdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Table(name = "student_courses")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StudentCourse {
    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();
    @ManyToOne
    @MapsId("studentId")
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    private Course course;
    private Integer ordinal;
}
