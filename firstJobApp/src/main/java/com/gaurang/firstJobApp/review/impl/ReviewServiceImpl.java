package com.gaurang.firstJobApp.review.impl;

import com.gaurang.firstJobApp.company.Company;
import com.gaurang.firstJobApp.company.CompanyService;
import com.gaurang.firstJobApp.review.Review;
import com.gaurang.firstJobApp.review.ReviewRepository;
import com.gaurang.firstJobApp.review.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public void addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (reviewRepository.findById(reviewId).isPresent()){
            if (companyService.getCompanyById(companyId) != null){
                updatedReview.setCompany(companyService.getCompanyById(companyId));
                updatedReview.setId(reviewId);
                reviewRepository.save(updatedReview);
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

//    @Override
//    public boolean deleteReview(Long companyId, Long reviewId) {
//        if (reviewRepository.findById(reviewId).isPresent()){
//            if (companyService.getCompanyById(companyId) != null){
//                Review review = reviewRepository.findById(reviewId).orElse(null);
//                Company company = review.getCompany();
//                company.getReviews().remove(review);
//                return true;
//            }else {
//                return false;
//            }
//        }
//        return false;
//    }

    @Transactional
    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review == null) {
            return false;
        }

        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        }

        if (!company.getReviews().contains(review)) {
            return false;
        }

        company.getReviews().remove(review);
        reviewRepository.delete(review);

        return true;
    }
}
