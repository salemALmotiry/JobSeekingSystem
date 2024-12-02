package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobPostService {


    private final UserService userService;
    private JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost() {
        return jobPostRepository.findAll();
    }


    public String addJobPost(Integer userId,JobPost jobPost) {

        User user = userService.getUserById(userId);
        if (user == null) {
            return "User Not Found";
        }
        if (!user.getRole().equals("EMPLOYER")) {
            return "User Role Not Allowed";
        }

        jobPost.setUserId(userId);
        jobPostRepository.save(jobPost);
        return null;

    }

    public String deleteJobPost(Integer postId) {
        for (JobPost jobPost : getAllJobPost()) {
            if (jobPost.getId() == postId) {
                jobPostRepository.delete(jobPost);
                return null;
            }
        }
        return "Job Post Not Found";
    }

    public String updateJobPost(Integer userId, Integer postId,JobPost jobPost) {



        if (!userId.equals(jobPost.getUserId())) {
            return "Post author Not Found";
        }
        for (JobPost oldJobPost : getAllJobPost()) {
            if (oldJobPost.getId() == postId) {

                oldJobPost.setTitle(jobPost.getTitle());
                oldJobPost.setDescription(jobPost.getDescription());
                oldJobPost.setPostingDate(jobPost.getPostingDate());
                jobPostRepository.save(oldJobPost);
                return null;
            }
        }
        return "Job Post Not Found";
    }

    public JobPost getJobPostById(Integer id) {
        for (JobPost jobPost : getAllJobPost()) {
            if (jobPost.getId() == id) {
                return jobPost;
            }
        }
        return null;
    }


}
