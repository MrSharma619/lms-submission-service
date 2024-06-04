package com.example.lmssubmissionservice.specifications;

import com.example.lmssubmissionservice.dto.SubmissionResponse;
import com.example.lmssubmissionservice.entity.Submission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SubmissionManager {

    SubmissionResponse submitTask(UUID taskId, String submissionUrl, UUID userId) throws Exception;

    Submission getSubmissionById(UUID submissionId) throws Exception;

    List<Submission> getAllSubmissionByTask(UUID taskId);

    List<Submission> getAllSubmissions();

    Submission reviewSubmission(UUID submissionId, String status) throws Exception;

}
