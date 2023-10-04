package shop.plant.shop.service;

import shop.plant.shop.model.ShoppingSession;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing shopping sessions.
 */
public interface ShoppingSessionService {
    /**
     * Save a new shopping session or update an existing one.
     *
     * @param shoppingSession The ShoppingSession object to be saved or updated.
     * @return The saved ShoppingSession object.
     */
    ShoppingSession saveShoppingSession(ShoppingSession shoppingSession);

    /**
     * Retrieve a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to retrieve.
     * @return An Optional containing the ShoppingSession object if found, or empty if not found.
     */
    Optional<ShoppingSession> getShoppingSessionById(Long id);

    /**
     * Retrieve all shopping sessions.
     *
     * @return A list of ShoppingSession objects representing all shopping sessions.
     */
    List<ShoppingSession> getAllShoppingSessions();

    /**
     * Update an existing shopping session.
     *
     * @param shoppingSession The ShoppingSession object to be updated.
     */
    void updateShoppingSession(ShoppingSession shoppingSession);

    /**
     * Delete a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to delete.
     */
    void deleteShoppingSession(Long id);

    // You can add more methods as needed for shopping session management.
}
