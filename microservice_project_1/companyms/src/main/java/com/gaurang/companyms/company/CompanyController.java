package com.gaurang.companyms.company;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> allCompanies = companyService.getAllCompanies();
        if (allCompanies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(allCompanies, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedCompany(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(id, company);
        if (updated)
            return new ResponseEntity<>("Company Updated SuccessFully",HttpStatus.OK);
        return new ResponseEntity<>("Company not found with this id :"+id,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        try {
            companyService.createCompany(company);
            return new ResponseEntity<>("Company Created SuccessFully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error creating company: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        try {
            Company company = companyService.getCompanyById(id);
            return new ResponseEntity<>(company,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleteCompany = companyService.deleteCompany(id);
        if (deleteCompany)
            return ResponseEntity.ok("Deleted SuccessFully");
        return new ResponseEntity<>("Company not found with this id :"+id,HttpStatus.NOT_FOUND);
    }

}
