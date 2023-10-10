package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.RatingDto;
import shop.plant.shop.model.Rating;
import shop.plant.shop.service.RatingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    /**
     * Create a new rating.
     *
     * @param ratingDto The RatingDto object containing the rating information.
     * @return A ResponseEntity containing the created RatingDto or an error response.
     */
    @PostMapping
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto) {
        try {
            // Convert RatingDto to Rating entity
            Rating rating = ratingService.convertToEntity(ratingDto);
            Rating savedRating = ratingService.saveRating(rating);

            // Convert the saved Rating back to a RatingDto
            RatingDto savedRatingDto = ratingService.convertToDto(savedRating);

            return new ResponseEntity<>(savedRatingDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a rating by its unique identifier (ID).
     *
     * @param id The ID of the rating to retrieve.
     * @return A ResponseEntity containing the retrieved RatingDto or a not found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable Long id) {
        Rating rating = ratingService.getRatingById(id);

        if (rating != null) {
            // Convert Rating to RatingDto
            RatingDto ratingDto = ratingService.convertToDto(rating);

            return new ResponseEntity<>(ratingDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all ratings.
     *
     * @return A ResponseEntity containing a list of RatingDto objects representing all ratings.
     */
    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        List<RatingDto> ratingDtos = ratingService.convertToDtoList(ratings);

        return new ResponseEntity<>(ratingDtos, HttpStatus.OK);
    }

    /**
     * Update an existing rating by its unique identifier (ID).
     *
     * @param id        The ID of the rating to update.
     * @param ratingDto The RatingDto object containing the updated rating information.
     * @return A ResponseEntity containing the updated RatingDto or an error response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        Rating existingRating = ratingService.getRatingById(id);

        if (existingRating != null) {
            try {
                // Convert RatingDto to Rating entity and set its ID
                Rating rating = ratingService.convertToEntity(ratingDto);
                rating.setId(id);

                Rating updatedRating = ratingService.updateRating(rating);

                // Convert the updated Rating back to a RatingDto
                RatingDto updatedRatingDto = ratingService.convertToDto(updatedRating);

                return new ResponseEntity<>(updatedRatingDto, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a rating by its unique identifier (ID).
     *
     * @param id The ID of the rating to delete.
     * @return A ResponseEntity indicating success or not found response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        Rating existingRating = ratingService.getRatingById(id);

        if (existingRating != null) {
            ratingService.deleteRating(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
