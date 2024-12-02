package com.example.jobseekingsystem.Controller;


import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-seeking-system/post")
public class JobPostController {
    
    private JobPostService jobPostService;


    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity addPost(@PathVariable Integer userId,@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String response =  jobPostService.addJobPost(userId, jobPost);
        if (response == null) {
            return ResponseEntity.status(200).body("Post added successfully");

        }
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{postId}/{userId}")
    public ResponseEntity updatePost(@PathVariable Integer postId,@PathVariable Integer userId, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        String response =  jobPostService.updateJobPost(userId, postId,jobPost);

        if (response == null) {
            return ResponseEntity.status(200).body("Post updated successfully");
        }
        return ResponseEntity.status(400).body(response);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable int postId) {

        String response =  jobPostService.deleteJobPost(postId);
        if (response == null) {
            return ResponseEntity.status(200).body("Post deleted successfully");
        }
        return ResponseEntity.status(400).body(response);
    }

}
