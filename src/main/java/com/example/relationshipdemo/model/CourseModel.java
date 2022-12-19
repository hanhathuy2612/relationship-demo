package com.example.relationshipdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CourseModel {
    private UUID id;
    private String name;
    private Set<UUID> studentIds;
    private Set<StudentModel> students;
}
