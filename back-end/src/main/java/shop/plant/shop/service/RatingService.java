package shop.plant.shop.service;

import shop.plant.shop.dto.RatingDto;
import shop.plant.shop.exception.UserException;
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

    /**
     * Convert a RatingDto to a Rating entity.
     *
     * @param ratingDto The RatingDto to be converted.
     * @return The corresponding Rating entity.
     */
    Rating convertToEntity(RatingDto ratingDto) throws UserException;

    /**
     * Convert a Rating entity to a RatingDto.
     *
     * @param rating The Rating entity to be converted.
     * @return The corresponding RatingDto.
     */
    RatingDto convertToDto(Rating rating);

    /**
     * Convert a list of Rating entities to a list of RatingDto objects.
     *
     * @param ratings The list of Rating entities to be converted.
     * @return The corresponding list of RatingDto objects.
     */
    List<RatingDto> convertToDtoList(List<Rating> ratings);
}
