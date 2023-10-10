package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.ReviewDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Product;
import shop.plant.shop.model.Review;
import shop.plant.shop.model.Users;
import shop.plant.shop.repositories.ReviewRepository;
import shop.plant.shop.service.ReviewService;
import shop.plant.shop.service.UsersService;
import shop.plant.shop.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the ReviewService interface.
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UsersService usersService;
    private final ProductService productService;

    /**
     * Save a new review.
     *
     * @param review The review to be saved.
     * @return The saved review.
     */
    @Override
    public Review saveReview(Review review) {
        // Set the creation timestamp to the current time
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    /**
     * Get a review by its unique identifier.
     *
     * @param id The unique identifier of the review.
     * @return The review with the specified ID, or null if not found.
     */
    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    /**
     * Get all reviews for a specific product.
     *
     * @param productId The ID of the product for which reviews are requested.
     * @return A list of reviews for the specified product.
     */
    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    /**
     * Update an existing review.
     *
     * @param review The updated review.
     * @return The updated review.
     */
    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Delete a review by its unique identifier.
     *
     * @param id The unique identifier of the review to be deleted.
     */
    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    /**
     * Convert a Review entity to a ReviewDto.
     *
     * @param review The Review entity to be converted.
     * @return The corresponding ReviewDto.
     */
    @Override
    public ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReview(review.getReview());
        reviewDto.setUserId(review.getUsers().getId());
        reviewDto.setProductId(review.getProduct().getId());
        reviewDto.setCreatedAt(review.getCreatedAt());
        return reviewDto;
    }

    /**
     * Convert a list of Review entities to a list of ReviewDto objects.
     *
     * @param reviews The list of Review entities to be converted.
     * @return The corresponding list of ReviewDto objects.
     */
    @Override
    public List<ReviewDto> convertToDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert a ReviewDto to a Review entity.
     *
     * @param reviewDto The ReviewDto to be converted.
     * @return The corresponding Review entity.
     */
    @Override
    public Review convertToEntity(ReviewDto reviewDto) throws UserException {
        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setReview(reviewDto.getReview());

        // You can retrieve the user and product entities by their IDs
        Users user = usersService.findUserById(reviewDto.getUserId());
        Product product = productService.getProductById(reviewDto.getProductId()).orElse(null);

        if (user != null) {
            review.setUsers(user);
        }

        if (product != null) {
            review.setProduct(product);
        }

        // Set the creation timestamp to the current time
        review.setCreatedAt(LocalDateTime.now());

        return review;
    }

}
