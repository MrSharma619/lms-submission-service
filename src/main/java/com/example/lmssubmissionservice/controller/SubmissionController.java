package com.example.lmssubmissionservice.controller;

import com.example.lmssubmissionservice.dto.MessageDto;
import com.example.lmssubmissionservice.dto.SubmissionResponse;
import com.example.lmssubmissionservice.dto.UserDto;
import com.example.lmssubmissionservice.entity.Submission;
import com.example.lmssubmissionservice.service.SubmissionService;
import com.example.lmssubmissionservice.specifications.NotificationManager;
import com.example.lmssubmissionservice.specifications.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private NotificationManager notificationManager;

    @PostMapping
    public ResponseEntity<Submission> submitTask(
            @RequestParam UUID taskId,
            @RequestParam String submissionUrl,
            @RequestHeader("Authorization") String token
    ) throws Exception {
        UserDto userDto = userManager.getUserProfile(token);

        SubmissionResponse submissionResponse = submissionService.submitTask(
                taskId,
                submissionUrl,
                userDto.getId());

        //send notification
        if(submissionResponse.getSubmission().getTaskId() == taskId &&
                submissionResponse.getSubmission().getStatus().equals("PENDING")){
            notificationManager.sendMessage(
                    new MessageDto(
                            userDto.getFullName() +
                                    " has made a submission for \"" +
                                    submissionResponse.getTaskTitle() +
                                    "\"",
                            submissionResponse.getTaskCreatedBy()
                    )
            );
        }

        return new ResponseEntity<>(submissionResponse.getSubmission(), HttpStatus.CREATED);

    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable UUID submissionId) throws Exception {
        Submission submission = submissionService.getSubmissionById(submissionId);

        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Submission>> getAllSubmissions(){
        List<Submission> submissions = submissionService.getAllSubmissions();

        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllSubmissionByTask(@PathVariable UUID taskId){
        List<Submission> submissions = submissionService.getAllSubmissionByTask(taskId);

        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PatchMapping("/{submissionId}")
    public ResponseEntity<Submission> reviewSubmission(
            @PathVariable UUID submissionId,
            @RequestParam String status) throws Exception {

        Submission submission = submissionService.reviewSubmission(submissionId, status);

        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

}
