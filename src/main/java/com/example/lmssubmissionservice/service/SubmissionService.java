package com.example.lmssubmissionservice.service;

import com.example.lmssubmissionservice.dto.TaskDto;
import com.example.lmssubmissionservice.entity.Submission;
import com.example.lmssubmissionservice.repository.SubmissionRepository;
import com.example.lmssubmissionservice.specifications.SubmissionManager;
import com.example.lmssubmissionservice.specifications.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubmissionService implements SubmissionManager {

    @Autowired
    private SubmissionRepository repository;

    @Autowired
    private TaskManager taskManager;

    @Override
    public Submission submitTask(UUID taskId, String submissionUrl, UUID userId) throws Exception {

        TaskDto taskDto = taskManager.getTaskById(taskId);

        if(taskDto != null){
            Submission submission = new Submission(
                    UUID.randomUUID(),
                    taskId,
                    submissionUrl,
                    userId,
                    "PENDING",
                    LocalDateTime.now()
            );

            return repository.save(submission);
        }

        throw new Exception("Task not found for id: " + taskId);

    }

    @Override
    public Submission getSubmissionById(UUID submissionId) throws Exception {
        return repository.findById(submissionId)
                .orElseThrow(() -> new Exception("No submission found with id: " + submissionId));
    }

    @Override
    public List<Submission> getAllSubmissionByTask(UUID taskId) {
        return repository.findByTaskId(taskId);
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return repository.findAll();
    }

    @Override
    public Submission reviewSubmission(UUID submissionId, String status) throws Exception {
        Submission submission = getSubmissionById(submissionId);

        submission.setStatus(status);

        //System.out.println("hi");

        if(status.equals("ACCEPT")){
            taskManager.completeTask(submission.getTaskId());

            //System.out.println(dto.toString());
        }

        return repository.save(submission);
    }

}
