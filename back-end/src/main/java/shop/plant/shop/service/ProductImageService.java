package shop.plant.shop.service;

import shop.plant.shop.dto.ProductImageDto;
import shop.plant.shop.model.ProductImage;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing product images.
 */
public interface ProductImageService {
    /**
     * Save a new product image or update an existing one.
     *
     * @param productImage The ProductImage object to be saved or updated.
     * @return The saved ProductImage object.
     */
    ProductImage saveProductImage(ProductImage productImage);

    /**
     * Retrieve a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to retrieve.
     * @return An Optional containing the ProductImage object if found, or empty if not found.
     */
    Optional<ProductImage> getProductImageById(Long id);

    /**
     * Retrieve all product images.
     *
     * @return A list of ProductImage objects representing all product images.
     */
    List<ProductImage> getAllProductImages();

    /**
     * Update an existing product image.
     *
     * @param productImage The ProductImage object to be updated.
     */
    void updateProductImage(ProductImage productImage);

    /**
     * Delete a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to delete.
     */
    void deleteProductImage(Long id);

    ProductImage convertToEntity(ProductImageDto productImageDto);

    ProductImageDto convertToDto(ProductImage savedProductImage);

    // You can add more methods as needed for product image management.
}
