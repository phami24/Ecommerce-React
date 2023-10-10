package shop.plant.shop.service;

import shop.plant.shop.dto.UserAddressDto;
import shop.plant.shop.model.UserAddress;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing user addresses.
 */
public interface UserAddressService {
    /**
     * Save a new user address or update an existing one.
     *
     * @param userAddress The UserAddress object to be saved or updated.
     * @return The saved UserAddress object.
     */
    UserAddress saveUserAddress(UserAddress userAddress);

    /**
     * Retrieve a user address by its unique identifier (ID).
     *
     * @param id The ID of the user address to retrieve.
     * @return An Optional containing the UserAddress object if found, or empty if not found.
     */
    Optional<UserAddress> getUserAddressById(Long id);

    /**
     * Retrieve all user addresses for a specific user.
     *
     * @param userId The ID of the user whose addresses are to be retrieved.
     * @return A list of UserAddress objects representing all user addresses for the specified user.
     */
    List<UserAddress> getUserAddressesByUserId(Long userId);

    /**
     * Update an existing user address.
     *
     * @param userAddress The UserAddress object to be updated.
     */
    void updateUserAddress(UserAddress userAddress);

    /**
     * Delete a user address by its unique identifier (ID).
     *
     * @param id The ID of the user address to delete.
     */
    void deleteUserAddress(Long id);

    /**
     * Convert a UserAddress entity to a UserAddressDto.
     *
     * @param userAddress The UserAddress entity to be converted.
     * @return The corresponding UserAddressDto.
     */
    UserAddressDto convertToDto(UserAddress userAddress);

    /**
     * Convert a list of UserAddress entities to a list of UserAddressDto objects.
     *
     * @param userAddresses The list of UserAddress entities to be converted.
     * @return The corresponding list of UserAddressDto objects.
     */
    List<UserAddressDto> convertToDtoList(List<UserAddress> userAddresses);

    /**
     * Convert a UserAddressDto to a UserAddress entity.
     *
     * @param userAddressDto The UserAddressDto to be converted.
     * @return The corresponding UserAddress entity.
     */
    UserAddress convertToEntity(UserAddressDto userAddressDto);
}
