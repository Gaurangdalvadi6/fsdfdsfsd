package com.gaurang.firstJobApp.job;

import com.gaurang.firstJobApp.company.Company;
import com.gaurang.firstJobApp.company.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    private final JobService jobService;

    public final CompanyRepository companyRepository;

    public JobController(JobService jobService, CompanyRepository companyRepository) {
        this.jobService = jobService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobService.findAll();

        if (jobs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        Long id = job.getCompany().getId();
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()){
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
        }
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
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
        Long id1 = updatedJob.getCompany().getId();
        Optional<Company> optionalCompany = companyRepository.findById(id1);
        if (optionalCompany.isEmpty()){
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
        }
        boolean updated = jobService.updateJob(id,updatedJob);
        if (updated)
            return new ResponseEntity<>("Job Updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("job not found with this id :"+id,HttpStatus.NOT_FOUND);
    }
}
