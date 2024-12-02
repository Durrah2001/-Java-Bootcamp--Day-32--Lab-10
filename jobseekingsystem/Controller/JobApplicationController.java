package org.example.jobseekingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.JobApplication;
import org.example.jobseekingsystem.Service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job_system/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplications(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/apply")
    public ResponseEntity applyJobApplication(@RequestBody JobApplication jobApplication){

        Integer result = jobApplicationService.applyJobApplication(jobApplication);

        switch (result){

            case -1:
                return ResponseEntity.status(400).body(new ApiResponse("User with this ID nor found! Can not apply unless there is an existing user!")); //user not found
            case -2:
                return ResponseEntity.status(400).body(new ApiResponse("Job post with this ID not found! Can not apply unless there is an existing job!"));

            case -3:
                return ResponseEntity.status(400).body(new ApiResponse("User applied for a job must be Job_seeker only!"));
            case 1:
                return ResponseEntity.status(200).body(new ApiResponse("Applied for this job done successfully!"));
            default:
                return ResponseEntity.status(400).body(new ApiResponse("Not found!"));

        }


    }

    @DeleteMapping("/withdraw/{id}")
    private ResponseEntity withdrawJobApplication(@PathVariable Integer id){
        Boolean isDeleted = jobApplicationService.deleteJobApplication(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Withdraw this job application done successfully!"));

        return ResponseEntity.status(400).body(new ApiResponse("Job application with this ID not found!"));
    }

















}//End controller
