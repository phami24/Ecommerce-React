package shop.plant.shop.service;

import shop.plant.shop.model.ProductInventory;

import java.util.Optional;

/**
 * Service interface for managing product inventory.
 */
public interface ProductInventoryService {
    /**
     * Retrieve the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id The ID of the product inventory to retrieve.
     * @return An Optional containing the quantity of the product in inventory if found, or empty if not found.
     */
    Optional<Integer> getQuantityByProductId(Long id);

    /**
     * Update the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id       The ID of the product inventory to update.
     * @param quantity The new quantity to set in the product inventory.
     */
    void updateQuantityByProductId(Long id, int quantity);

     Optional<ProductInventory> getInventoryById(Long inventoryId);

    // You can add more methods as needed for product inventory management.
}
