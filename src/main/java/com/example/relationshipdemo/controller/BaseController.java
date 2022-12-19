package com.example.relationshipdemo.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseController <E, M, I>{
    private final JpaRepository<E, I> repository;

    protected BaseController(JpaRepository<E, I> repository) {
        this.repository = repository;
    }

}
