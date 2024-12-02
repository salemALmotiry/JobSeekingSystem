package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationService {

    private final UserService userService;
    private final JobPostService jobPostService;
    private JobApplicationRepository jobApplicationRepository;


    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }



    public String applyJobApplication(Integer userId,Integer jobPostId, JobApplication jobApplication) {
        User user  = userService.getUserById(userId);
        if (user == null) {
            return "User Not Found";
        }

        JobPost jobPost = jobPostService.getJobPostById(jobPostId);
        if (jobPost == null) {
            return "Job Post Not Found";
        }

        jobApplication.setUserId(userId);
        jobApplication.setJobPostId(jobPostId);
        jobApplicationRepository.save(jobApplication);
        return null;
    }


    public String withdrawJobApplication(Integer id) {
        for (JobApplication jobApplication : getAllJobApplications()) {
            if (jobApplication.getId().equals(id)) {
                jobApplicationRepository.delete(jobApplication);
                return null;
            }
        }
        return "Job Application Not Found";
    }


    public JobApplication getJobApplication(Integer id) {

        for (JobApplication jobApplication : getAllJobApplications()) {
            if (jobApplication.getId().equals(id)) {
                return jobApplication;
            }
        }

        return null;
    }


}
