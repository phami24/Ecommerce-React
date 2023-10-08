package shop.plant.shop.service;

import shop.plant.shop.model.Rating;

import java.util.List;

/**
 * This interface defines the methods for managing ratings.
 */
public interface RatingService {
    /**
     * Save a new rating.
     *
     * @param rating The rating to be saved.
     * @return The saved rating.
     */
    Rating saveRating(Rating rating);

    /**
     * Get a rating by its unique identifier.
     *
     * @param id The unique identifier of the rating.
     * @return The rating with the specified ID, or null if not found.
     */
    Rating getRatingById(Long id);

    /**
     * Get all ratings.
     *
     * @return A list of all ratings.
     */
    List<Rating> getAllRatings();

    /**
     * Update an existing rating.
     *
     * @param rating The updated rating.
     * @return The updated rating.
     */
    Rating updateRating(Rating rating);

    /**
     * Delete a rating by its unique identifier.
     *
     * @param id The unique identifier of the rating to be deleted.
     */
    void deleteRating(Long id);
}
