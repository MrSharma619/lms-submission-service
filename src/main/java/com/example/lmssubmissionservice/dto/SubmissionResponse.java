package com.example.lmssubmissionservice.dto;

import com.example.lmssubmissionservice.entity.Submission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {
    private Submission submission;

    private String taskTitle;

    private UUID taskCreatedBy;
}
