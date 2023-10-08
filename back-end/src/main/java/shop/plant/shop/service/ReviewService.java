package shop.plant.shop.service;

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
}
