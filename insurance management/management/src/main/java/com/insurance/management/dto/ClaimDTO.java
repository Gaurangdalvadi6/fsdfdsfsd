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
public class ClaimDTO {

    private Long id;
    private Double amount;
    private Date dateOfIncident;
    private String description;
    private String status;
    private Long policyId;
    private Long customerId;
}
