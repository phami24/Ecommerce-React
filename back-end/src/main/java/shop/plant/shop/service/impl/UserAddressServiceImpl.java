package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.UserAddressDto;
import shop.plant.shop.model.UserAddress;
import shop.plant.shop.repositories.UserAddressRepository;
import shop.plant.shop.service.UserAddressService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the UserAddressService interface for managing user addresses.
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserAddressServiceImpl(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    /**
     * Save a new user address or update an existing one.
     *
     * @param userAddress The UserAddress object to be saved or updated.
     * @return The saved UserAddress object.
     */
    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    /**
     * Retrieve a user address by its unique identifier (ID).
     *
     * @param id The ID of the user address to retrieve.
     * @return An Optional containing the UserAddress object if found, or empty if not found.
     */
    @Override
    public Optional<UserAddress> getUserAddressById(Long id) {
        return userAddressRepository.findById(id);
    }

    /**
     * Retrieve all user addresses for a specific user.
     *
     * @param userId The ID of the user whose addresses are to be retrieved.
     * @return A list of UserAddress objects representing all user addresses for the specified user.
     */
    @Override
    public List<UserAddress> getUserAddressesByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId);
    }

    /**
     * Update an existing user address.
     *
     * @param userAddress The UserAddress object to be updated.
     */
    @Override
    public void updateUserAddress(UserAddress userAddress) {
        userAddressRepository.save(userAddress);
    }

    /**
     * Delete a user address by its unique identifier (ID).
     *
     * @param id The ID of the user address to delete.
     */
    @Override
    public void deleteUserAddress(Long id) {
        userAddressRepository.deleteById(id);
    }

    /**
     * Convert a UserAddress entity to a UserAddressDto.
     *
     * @param userAddress The UserAddress entity to be converted.
     * @return The corresponding UserAddressDto.
     */
    @Override
    public UserAddressDto convertToDto(UserAddress userAddress) {
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setId(userAddress.getId());
        userAddressDto.setAddressLineOne(userAddress.getAddressLineOne());
        userAddressDto.setAddressLineTwo(userAddress.getAddressLineTwo());
        userAddressDto.setCity(userAddress.getCity());
        userAddressDto.setPostalCode(userAddress.getPostalCode());
        userAddressDto.setCountry(userAddress.getCountry());

        // Set the user ID associated with the address
        if (userAddress.getUser() != null) {
            userAddressDto.setUserId(userAddress.getUser().getId());
        }

        return userAddressDto;
    }

    /**
     * Convert a list of UserAddress entities to a list of UserAddressDto objects.
     *
     * @param userAddresses The list of UserAddress entities to be converted.
     * @return The corresponding list of UserAddressDto objects.
     */
    @Override
    public List<UserAddressDto> convertToDtoList(List<UserAddress> userAddresses) {
        return userAddresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert a UserAddressDto to a UserAddress entity.
     *
     * @param userAddressDto The UserAddressDto to be converted.
     * @return The corresponding UserAddress entity.
     */
    @Override
    public UserAddress convertToEntity(UserAddressDto userAddressDto) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressDto.getId());
        userAddress.setAddressLineOne(userAddressDto.getAddressLineOne());
        userAddress.setAddressLineTwo(userAddressDto.getAddressLineTwo());
        userAddress.setCity(userAddressDto.getCity());
        userAddress.setPostalCode(userAddressDto.getPostalCode());
        userAddress.setCountry(userAddressDto.getCountry());

        // Set the associated user based on the user ID
        if (userAddressDto.getUserId() != null) {
            // You can fetch the user entity here if needed
            // userAddress.setUser(userService.getUserById(userAddressDto.getUserId()).orElse(null));
        }

        return userAddress;
    }
}
