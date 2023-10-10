package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import shop.plant.shop.dto.RatingDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Product;
import shop.plant.shop.model.Rating;
import shop.plant.shop.model.Users;
import shop.plant.shop.repositories.RatingRepository;
import shop.plant.shop.service.ProductService;
import shop.plant.shop.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.service.UsersService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the RatingService interface.
 */
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final UsersService usersService;
    private final ProductService productService;

    /**
     * Save a new rating or update an existing one.
     *
     * @param rating The Rating object to be saved or updated.
     * @return The saved Rating object.
     */
    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Retrieve a rating by its unique identifier (ID).
     *
     * @param id The ID of the rating to retrieve.
     * @return The Rating object if found, or null if not found.
     */
    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Retrieve all ratings.
     *
     * @return A list of Rating objects representing all ratings.
     */
    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Update an existing rating.
     *
     * @param rating The Rating object to be updated.
     * @return The updated Rating object.
     */
    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Delete a rating by its unique identifier (ID).
     *
     * @param id The ID of the rating to delete.
     */
    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    /**
     * Convert a Rating entity to a RatingDto.
     *
     * @param rating The Rating entity to be converted.
     * @return The corresponding RatingDto.
     */
    @Override
    public RatingDto convertToDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setUserId(rating.getUsers().getId());
        ratingDto.setProductId(rating.getProduct().getId());
        ratingDto.setRating(rating.getRating());
        return ratingDto;
    }

    /**
     * Convert a list of Rating entities to a list of RatingDto objects.
     *
     * @param ratings The list of Rating entities to be converted.
     * @return The corresponding list of RatingDto objects.
     */
    public List<RatingDto> convertToDtoList(List<Rating> ratings) {
        return ratings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert a RatingDto to a Rating entity.
     *
     * @param ratingDto The RatingDto to be converted.
     * @return The corresponding Rating entity.
     */
    @Override
    public Rating convertToEntity(RatingDto ratingDto) throws UserException {
        Rating rating = new Rating();

        rating.setId(ratingDto.getId());
        Users users = usersService.findUserById(ratingDto.getUserId());
        if (users != null) {
            rating.setUsers(users);
        }
        Product product = productService.getProductById(ratingDto.getProductId()).orElse(null);
        if (product != null) {
            rating.setProduct(product);
        }
        rating.setRating(ratingDto.getRating());
        return rating;
    }
}
