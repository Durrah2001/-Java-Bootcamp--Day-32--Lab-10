package org.example.jobseekingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.Model.JobApplication;
import org.example.jobseekingsystem.Model.JobPost;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Repository.JobApplicationRepository;
import org.example.jobseekingsystem.Repository.JobPostRepository;
import org.example.jobseekingsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;


    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }


    //Apply for job
    public Integer applyJobApplication(JobApplication jobApplication) {


        // Check if user exits in DB
        Optional<User> userOpt = userRepository.findById(jobApplication.getUserId());

        if (!userOpt.isPresent()) {
            return -1;
        }

        // Check if the user is with "JOB_SEEKER" role
        User user = userOpt.get();
        if (!user.getRole().equalsIgnoreCase("JOB_SEEKER")) {
            return -3;
        }

        // Check if the jobpost exist
        Optional<JobPost> jobPostOpt = jobPostRepository.findById(jobApplication.getJobPostId());

        if (!jobPostOpt.isPresent()) {
            return -2;  //not found
        }

        jobApplication.setUserId(jobApplication.getUserId());
        jobApplication.setJobPostId(jobApplication.getJobPostId());

        //Save to DB
        jobApplicationRepository.save(jobApplication);

        return 1;
    }



    //Withdraw an application for job
    public Boolean deleteJobApplication(Integer id){

        JobApplication oldJobApplication = jobApplicationRepository.getById(id);

        if(oldJobApplication == null)

            return false;

        jobApplicationRepository.delete(oldJobApplication);
        return true;


    }



}//End service
