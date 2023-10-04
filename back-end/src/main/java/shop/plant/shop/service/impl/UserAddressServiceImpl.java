package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.UserAddress;
import shop.plant.shop.repositories.UserAddressRepository;
import shop.plant.shop.service.UserAddressService;

import java.util.List;
import java.util.Optional;

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

    // You can implement additional methods as needed for user address management.
}
