package com.insurance.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {

    private Long id;
    private String policyNumber;
    private String type;
    private Double coverageAmount;
    private Date startDate;
    private Date endDate;
    private Double premium;
    private Long customerId;
}
