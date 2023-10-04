package shop.plant.shop.service;

import shop.plant.shop.model.OrderDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing order details.
 */
public interface OrderDetailsService {
    /**
     * Save a new order details or update an existing one.
     *
     * @param orderDetails The OrderDetails object to be saved or updated.
     * @return The saved OrderDetails object.
     */
    OrderDetails saveOrderDetails(OrderDetails orderDetails);

    /**
     * Retrieve order details by its unique identifier (ID).
     *
     * @param id The ID of the order details to retrieve.
     * @return An Optional containing the OrderDetails object if found, or empty if not found.
     */
    Optional<OrderDetails> getOrderDetailsById(Long id);

    /**
     * Retrieve all order details.
     *
     * @return A list of OrderDetails objects representing all order details.
     */
    List<OrderDetails> getAllOrderDetails();

    /**
     * Update existing order details.
     *
     * @param orderDetails The OrderDetails object to be updated.
     */
    void updateOrderDetails(OrderDetails orderDetails);

    /**
     * Delete order details by its unique identifier (ID).
     *
     * @param id The ID of the order details to delete.
     */
    void deleteOrderDetails(Long id);

    // You can add more methods as needed for order details management.
}
