package com.example.lmssubmissionservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Submission {

    @Id
    private UUID id;

    private UUID taskId;

    private String submissionUrl;

    private UUID userId;

    private String status;

    private LocalDateTime submissionTime;

}
