package com.insurance.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInfo;
    private String address;
    private String identificationDetails;

//    @OneToMany(mappedBy = "customer")
//    private List<Policy> policies;
//
//    @OneToMany(mappedBy = "customer")
//    private List<Claim> claims;
}
