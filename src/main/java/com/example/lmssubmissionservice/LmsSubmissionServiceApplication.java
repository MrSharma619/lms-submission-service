package com.example.lmssubmissionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LmsSubmissionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsSubmissionServiceApplication.class, args);
	}

}
