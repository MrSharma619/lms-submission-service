package com.example.lmssubmissionservice.specifications;

import com.example.lmssubmissionservice.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//configure open feign to communicate with other microservices
@FeignClient(name = "TASK-SERVICE", url = "http://localhost:5002")
public interface TaskManager {

    @GetMapping("/api/task/{taskId}")
    TaskDto getTaskById(@PathVariable UUID taskId) throws Exception;


    //@PatchMapping("/api/task/{taskId}/complete")
    //open feign doesnt support patch mapping
    //getting error

    @PutMapping("/api/task/{taskId}/complete")
    TaskDto completeTask(@PathVariable UUID taskId) throws Exception;

}
