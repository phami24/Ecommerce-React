package shop.plant.shop.service;

import shop.plant.shop.dto.ReviewDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Review;

import java.util.List;

/**
 * This interface defines the methods for managing product reviews.
 */
public interface ReviewService {
    /**
     * Save a new review.
     *
     * @param review The review to be saved.
     * @return The saved review.
     */
    Review saveReview(Review review);

    /**
     * Get a review by its unique identifier.
     *
     * @param id The unique identifier of the review.
     * @return The review with the specified ID, or null if not found.
     */
    Review getReviewById(Long id);

    /**
     * Get all reviews for a specific product.
     *
     * @param productId The ID of the product for which reviews are requested.
     * @return A list of reviews for the specified product.
     */
    List<Review> getReviewsByProductId(Long productId);

    /**
     * Update an existing review.
     *
     * @param review The updated review.
     * @return The updated review.
     */
    Review updateReview(Review review);

    /**
     * Delete a review by its unique identifier.
     *
     * @param id The unique identifier of the review to be deleted.
     */
    void deleteReview(Long id);

    /**
     * Convert a Review entity to a ReviewDto.
     *
     * @param review The Review entity to be converted.
     * @return The corresponding ReviewDto.
     */

    ReviewDto convertToDto(Review review);

    /**
     * Convert a list of Review entities to a list of ReviewDto objects.
     *
     * @param reviews The list of Review entities to be converted.
     * @return The corresponding list of ReviewDto objects.
     */
    List<ReviewDto> convertToDtoList(List<Review> reviews);

    /**
     * Convert a ReviewDto to a Review entity.
     *
     * @param reviewDto The ReviewDto to be converted.
     * @return The corresponding Review entity.
     */
    Review convertToEntity(ReviewDto reviewDto) throws UserException;
}
