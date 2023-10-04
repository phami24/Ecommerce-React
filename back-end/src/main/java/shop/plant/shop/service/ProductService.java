package shop.plant.shop.service;

import shop.plant.shop.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing products.
 */
public interface ProductService {
    /**
     * Save a new product or update an existing one.
     *
     * @param product The Product object to be saved or updated.
     * @return The saved Product object.
     */
    Product saveProduct(Product product);

    /**
     * Retrieve a product by its unique identifier (ID).
     *
     * @param id The ID of the product to retrieve.
     * @return An Optional containing the Product object if found, or empty if not found.
     */
    Optional<Product> getProductById(Long id);

    /**
     * Retrieve all products.
     *
     * @return A list of Product objects representing all products.
     */
    List<Product> getAllProducts();

    /**
     * Update an existing product.
     *
     * @param product The Product object to be updated.
     */
    void updateProduct(Product product);

    /**
     * Delete a product by its unique identifier (ID).
     *
     * @param id The ID of the product to delete.
     */
    void deleteProduct(Long id);

    // You can add more methods as needed for product management.
}
