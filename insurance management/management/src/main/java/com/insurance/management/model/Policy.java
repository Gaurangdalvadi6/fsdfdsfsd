package com.insurance.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private String type;
    private Double coverageAmount;
    private Date startDate;
    private Date endDate;
    private Double premium;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
