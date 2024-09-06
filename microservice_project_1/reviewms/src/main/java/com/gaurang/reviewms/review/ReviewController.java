package com.gaurang.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review){
        boolean isReviewAdded = reviewService.addReview(companyId, review);
        if (isReviewAdded) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review Not Updated",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        Review review1 = reviewService.getReview(reviewId);
        if (review1!= null)
            return new ResponseEntity<>(review1,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(reviewId, review);
        if (isUpdated){
            return new ResponseEntity<>("Review updated Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review Not Updated",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted){
            return new ResponseEntity<>("Review deleted Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Review Not Deleted",HttpStatus.BAD_REQUEST);
        }
    }

}
