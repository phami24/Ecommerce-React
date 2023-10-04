package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.ProductImage;
import shop.plant.shop.repositories.ProductImageRepository;
import shop.plant.shop.service.ProductImageService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductImageService interface for managing product images.
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    /**
     * Save a new product image or update an existing one.
     *
     * @param productImage The ProductImage object to be saved or updated.
     * @return The saved ProductImage object.
     */
    @Override
    public ProductImage saveProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    /**
     * Retrieve a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to retrieve.
     * @return An Optional containing the ProductImage object if found, or empty if not found.
     */
    @Override
    public Optional<ProductImage> getProductImageById(Long id) {
        return productImageRepository.findById(id);
    }

    /**
     * Retrieve all product images.
     *
     * @return A list of ProductImage objects representing all product images.
     */
    @Override
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    /**
     * Update an existing product image.
     *
     * @param productImage The ProductImage object to be updated.
     */
    @Override
    public void updateProductImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    /**
     * Delete a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to delete.
     */
    @Override
    public void deleteProductImage(Long id) {
        productImageRepository.deleteById(id);
    }

    // You can implement additional methods as needed for product image management.
}
