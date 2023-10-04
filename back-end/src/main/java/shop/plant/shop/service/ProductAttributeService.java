package shop.plant.shop.service;

import shop.plant.shop.model.ProductAttribute;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing product attributes.
 */
public interface ProductAttributeService {
    /**
     * Save a new product attribute or update an existing one.
     *
     * @param productAttribute The ProductAttribute object to be saved or updated.
     * @return The saved ProductAttribute object.
     */
    ProductAttribute saveProductAttribute(ProductAttribute productAttribute);

    /**
     * Retrieve a product attribute by its unique identifier (ID).
     *
     * @param id The ID of the product attribute to retrieve.
     * @return An Optional containing the ProductAttribute object if found, or empty if not found.
     */
    Optional<ProductAttribute> getProductAttributeById(Long id);

    /**
     * Retrieve all product attributes.
     *
     * @return A list of ProductAttribute objects representing all product attributes.
     */
    List<ProductAttribute> getAllProductAttributes();

    /**
     * Update an existing product attribute.
     *
     * @param productAttribute The ProductAttribute object to be updated.
     */
    void updateProductAttribute(ProductAttribute productAttribute);

    /**
     * Delete a product attribute by its unique identifier (ID).
     *
     * @param id The ID of the product attribute to delete.
     */
    void deleteProductAttribute(Long id);

    // You can add more methods as needed for product attribute management.
}
