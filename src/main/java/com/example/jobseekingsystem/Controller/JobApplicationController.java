package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.ApiResponse.ApiResponse;
import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-seeking-system/jop-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping("/apply/{userId}/{jobPostId}")
    public ResponseEntity addJobApplication(@PathVariable Integer userId, @PathVariable Integer jobPostId, @RequestBody @Valid JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        String response = jobApplicationService.applyJobApplication(userId,jobPostId,jobApplication);

        if (response == null) {

            return ResponseEntity.status(200).body(new ApiResponse("Job application added successfully"));
        }

        return ResponseEntity.status(400).body(response);
    }



    @DeleteMapping("/withdraw-job-application/{jobApplicationId}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer jobApplicationId) {
        String response = jobApplicationService.withdrawJobApplication(jobApplicationId);
        if (response == null) {
            return ResponseEntity.status(200).body(new ApiResponse("Job application delete successfully"));

        }
        return ResponseEntity.status(400).body(response);
    }


}
