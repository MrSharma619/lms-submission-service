package com.example.lmssubmissionservice.specifications;

import com.example.lmssubmissionservice.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE", url = "http://localhost:5004")
public interface NotificationManager {

    @PostMapping("/api/notification/send")
    public void sendMessage(@RequestBody MessageDto dto);

}
