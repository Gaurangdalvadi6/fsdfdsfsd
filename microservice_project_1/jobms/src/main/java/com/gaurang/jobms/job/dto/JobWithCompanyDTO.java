package com.gaurang.jobms.job.dto;

import com.gaurang.jobms.job.Job;
import com.gaurang.jobms.job.external.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobWithCompanyDTO {

    private Job job;
    private Company company;
}
