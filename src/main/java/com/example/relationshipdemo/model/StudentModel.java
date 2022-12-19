package com.example.relationshipdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StudentModel {
    private UUID id;
    private String name;
    private int ordinal;
}
