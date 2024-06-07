package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.payload.ApiResponse;
import com.product.payload.SellerDto;
import com.product.service.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping
    public ResponseEntity<?> createSeller(@Valid @RequestBody SellerDto sellerDTO) {
        SellerDto createdSeller = sellerService.createSeller(sellerDTO);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }
	
	@GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers() {
        List<SellerDto> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }
	
	@GetMapping("/{sellerid}")
    public ResponseEntity<SellerDto> getSellerById(@PathVariable Long sellerid) {
        SellerDto seller = sellerService.getSellerById(sellerid);
        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return new ApiResponse("Seller Deleted Successfully!!!", true);
    }
}
