package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.ReviewDto;
import shop.plant.shop.model.Review;
import shop.plant.shop.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * Create a new review.
     *
     * @param reviewDto The ReviewDto representing the new review.
     * @return A ResponseEntity containing the created ReviewDto.
     */
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        try {
            // Convert ReviewDto to Review entity
            Review review = reviewService.convertToEntity(reviewDto);
            Review savedReview = reviewService.saveReview(review);

            // Convert the saved Review back to a ReviewDto
            ReviewDto savedReviewDto = reviewService.convertToDto(savedReview);

            return new ResponseEntity<>(savedReviewDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a review by its unique identifier (ID).
     *
     * @param id The ID of the review to retrieve.
     * @return A ResponseEntity containing the ReviewDto if found, or an error message if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);

        if (review != null) {
            // Convert Review to ReviewDto
            ReviewDto reviewDto = reviewService.convertToDto(review);
            return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all reviews for a specific product.
     *
     * @param productId The ID of the product for which reviews are requested.
     * @return A ResponseEntity containing a list of ReviewDto objects for the specified product.
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);

        // Convert the list of Review entities to a list of ReviewDto objects
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(reviewService::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    /**
     * Update an existing review.
     *
     * @param id        The ID of the review to update.
     * @param reviewDto The ReviewDto representing the updated review.
     * @return A ResponseEntity containing the updated ReviewDto, or an error message if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        Review existingReview = reviewService.getReviewById(id);

        if (existingReview != null) {
            try {
                // Convert ReviewDto to Review entity
                Review updatedReview = reviewService.convertToEntity(reviewDto);
                updatedReview.setId(id);
                Review savedReview = reviewService.updateReview(updatedReview);

                // Convert the saved Review back to a ReviewDto
                ReviewDto savedReviewDto = reviewService.convertToDto(savedReview);

                return new ResponseEntity<>(savedReviewDto, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a review by its unique identifier (ID).
     *
     * @param id The ID of the review to delete.
     * @return A ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        Review existingReview = reviewService.getReviewById(id);

        if (existingReview != null) {
            reviewService.deleteReview(id);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }
}
