package com.example.lmssubmissionservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskStatus {
    PENDING("PENDING"),

    ASSIGNED("ASSIGNED"),

    DONE("DONE");

    private String status;

}
