package com.gaurang.jobms.job;


import com.gaurang.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findAll(){
        List<JobDTO> jobDTOS = jobService.findAll();

        if (jobDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(jobDTOS);
    }


    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO job = jobService.getJobById(id);
        if (job!=null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted)
            return new ResponseEntity<>("job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("job not found with this id :"+id,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);
        if (updated)
            return new ResponseEntity<>("Job Updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("job not found with this id :"+id,HttpStatus.NOT_FOUND);
    }
}
