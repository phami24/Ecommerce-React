package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.ProductInventory;
import shop.plant.shop.repositories.ProductInventoryRepository;
import shop.plant.shop.service.ProductInventoryService;

import java.util.Optional;

/**
 * Implementation of the ProductInventoryService interface for managing product inventory.
 */
@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;

    @Autowired
    public ProductInventoryServiceImpl(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }

    /**
     * Retrieve the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id The ID of the product inventory to retrieve.
     * @return An Optional containing the quantity of the product in inventory if found, or empty if not found.
     */
    @Override
    public Optional<Integer> getQuantityByProductId(Long id) {
        Optional<ProductInventory> productInventory = productInventoryRepository.findById(id);
        return productInventory.map(ProductInventory::getQuantity);
    }

    /**
     * Update the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id       The ID of the product inventory to update.
     * @param quantity The new quantity to set in the product inventory.
     */
    @Override
    public void updateQuantityByProductId(Long id, int quantity) {
        Optional<ProductInventory> productInventoryOptional = productInventoryRepository.findById(id);
        if (productInventoryOptional.isPresent()) {
            ProductInventory productInventory = productInventoryOptional.get();
            productInventory.setQuantity(quantity);
            productInventoryRepository.save(productInventory);
        }
    }

    @Override
    public Optional<ProductInventory> getInventoryById(Long inventoryId) {
        return productInventoryRepository.findById(inventoryId);
    }

    // You can implement additional methods as needed for product inventory management.
}
