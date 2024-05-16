package com.example.lmssubmissionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private UUID id;

    private String title;

    private String description;

    private String imageUrl;

    private UUID createdByUserId;

    private List<UUID> assignedUserId;

    //initialized it as well otherwise getting null in response instead of []
    private List<String> tags = new ArrayList<>();               //tech stack of assignment

    private LocalDateTime deadline;

    private LocalDateTime createdAt;

    private TaskStatus status;

}
