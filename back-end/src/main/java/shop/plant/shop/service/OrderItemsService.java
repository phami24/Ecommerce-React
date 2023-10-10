package shop.plant.shop.service;

import shop.plant.shop.dto.OrderItemsDto;
import shop.plant.shop.model.OrderItems;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing order items.
 */
public interface OrderItemsService {
    /**
     * Save a new order item or update an existing one.
     *
     * @param orderItem The OrderItems object to be saved or updated.
     * @return The saved OrderItems object.
     */
    OrderItems saveOrderItem(OrderItems orderItem);

    /**
     * Retrieve order item by its unique identifier (ID).
     *
     * @param id The ID of the order item to retrieve.
     * @return An Optional containing the OrderItems object if found, or empty if not found.
     */
    Optional<OrderItems> getOrderItemById(Long id);

    /**
     * Retrieve all order items.
     *
     * @return A list of OrderItems objects representing all order items.
     */
    List<OrderItems> getAllOrderItems();

    /**
     * Update existing order item.
     *
     * @param orderItem The OrderItems object to be updated.
     */
    void updateOrderItem(OrderItems orderItem);

    /**
     * Delete order item by its unique identifier (ID).
     *
     * @param id The ID of the order item to delete.
     */
    void deleteOrderItem(Long id);

    OrderItemsDto convertToDto(OrderItems orderItem);

    List<OrderItems> getOrderItemsByOrderDetailId(Long id);
}
