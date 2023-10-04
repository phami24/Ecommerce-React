package shop.plant.shop.service;

import shop.plant.shop.model.ProductCategory;

import java.util.List;
import java.util.Optional;

/**d
 * Service interface for managing product categories.
 */
public interface ProductCategoryService {
    /**
     * Save a new product category or update an existing one.
     *
     * @param productCategory The ProductCategory object to be saved or updated.
     * @return The saved ProductCategory object.
     */
    ProductCategory saveProductCategory(ProductCategory productCategory);

    /**
     * Retrieve a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to retrieve.
     * @return An Optional containing the ProductCategory object if found, or empty if not found.
     */
    Optional<ProductCategory> getProductCategoryById(Long id);

    /**
     * Retrieve all product categories.
     *
     * @return A list of ProductCategory objects representing all product categories.
     */
    List<ProductCategory> getAllProductCategories();

    /**
     * Update an existing product category.
     *
     * @param productCategory The ProductCategory object to be updated.
     */
    void updateProductCategory(ProductCategory productCategory);

    /**
     * Delete a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to delete.
     */
    void deleteProductCategory(Long id);

    // You can add more methods as needed for product category management.
}
