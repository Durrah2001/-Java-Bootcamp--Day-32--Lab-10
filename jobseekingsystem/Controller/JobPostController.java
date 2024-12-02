package org.example.jobseekingsystem.Controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.JobPost;
import org.example.jobseekingsystem.Service.JobPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-system/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;


    @GetMapping("/get")
    public ResponseEntity getJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){

        if(errors.hasErrors())
         return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job post added successfully!"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());


        Boolean isUpdated = jobPostService.updateJobPost(id, jobPost);

        if(isUpdated)

            return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully!"));

        return ResponseEntity.status(400).body(new ApiResponse("Job post with this ID not found!"));


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){

        Boolean isDeleted = jobPostService.deleteJobPost(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully!"));


        return ResponseEntity.status(400).body(new ApiResponse("Job post with this ID not found!"));


    }
















}//End controller
