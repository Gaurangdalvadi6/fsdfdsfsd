package com.gaurang.jobms.job.impl;

import com.gaurang.jobms.job.Job;
import com.gaurang.jobms.job.JobRepository;
import com.gaurang.jobms.job.JobService;
import com.gaurang.jobms.job.dto.JobDTO;
import com.gaurang.jobms.job.external.Company;
import com.gaurang.jobms.job.external.Review;
import com.gaurang.jobms.job.mapper.JobMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final RestTemplate restTemplate;


    public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDto)
                .toList();
    }

    private JobDTO convertToDto(Job job){
        Company company = restTemplate.getForObject(
                "http://COMPANY-SERVICE/companies/"+job.getCompanyId(),
                Company.class);
        ResponseEntity<List<Review>> responseReview = restTemplate.exchange(
                "http://REVIEW-SERVICE/reviews?companyId="+job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = responseReview.getBody();

        return JobMapper.mapToJobWithCompanyDto(job,company,reviews);
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null){
            return null;
        }
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()){
            Job job = optionalJob.get();
            jobRepository.delete(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
            if (optionalJob.isPresent()) {
                Job job = optionalJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }


}
