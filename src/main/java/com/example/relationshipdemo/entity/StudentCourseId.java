package com.example.relationshipdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
@Embeddable
public class StudentCourseId implements Serializable {
    private UUID studentId;
    private UUID courseId;
}
