package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.ShoppingSession;
import shop.plant.shop.repositories.ShoppingSessionRepository;
import shop.plant.shop.service.ShoppingSessionService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ShoppingSessionService interface for managing shopping sessions.
 */
@Service
public class ShoppingSessionServiceImpl implements ShoppingSessionService {
    private final ShoppingSessionRepository shoppingSessionRepository;

    @Autowired
    public ShoppingSessionServiceImpl(ShoppingSessionRepository shoppingSessionRepository) {
        this.shoppingSessionRepository = shoppingSessionRepository;
    }

    /**
     * Save a new shopping session or update an existing one.
     *
     * @param shoppingSession The ShoppingSession object to be saved or updated.
     * @return The saved ShoppingSession object.
     */
    @Override
    public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
        return shoppingSessionRepository.save(shoppingSession);
    }

    /**
     * Retrieve a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to retrieve.
     * @return An Optional containing the ShoppingSession object if found, or empty if not found.
     */
    @Override
    public Optional<ShoppingSession> getShoppingSessionById(Long id) {
        return shoppingSessionRepository.findById(id);
    }

    /**
     * Retrieve all shopping sessions.
     *
     * @return A list of ShoppingSession objects representing all shopping sessions.
     */
    @Override
    public List<ShoppingSession> getAllShoppingSessions() {
        return shoppingSessionRepository.findAll();
    }

    /**
     * Update an existing shopping session.
     *
     * @param shoppingSession The ShoppingSession object to be updated.
     */
    @Override
    public void updateShoppingSession(ShoppingSession shoppingSession) {
        shoppingSessionRepository.save(shoppingSession);
    }

    /**
     * Delete a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to delete.
     */
    @Override
    public void deleteShoppingSession(Long id) {
        shoppingSessionRepository.deleteById(id);
    }

    // You can implement additional methods as needed for shopping session management.
}
