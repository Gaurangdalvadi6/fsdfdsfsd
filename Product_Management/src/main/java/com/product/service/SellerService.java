package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.payload.SellerDto;

@Service
public interface SellerService {

	SellerDto createSeller(SellerDto sellerDTO);

	List<SellerDto> getAllSellers();

	SellerDto getSellerById(Long id);

	void deleteSeller(Long id);

	

	
}
