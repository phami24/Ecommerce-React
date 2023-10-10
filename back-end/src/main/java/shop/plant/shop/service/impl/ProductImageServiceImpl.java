package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.ProductImageDto;
import shop.plant.shop.model.ProductImage;
import shop.plant.shop.repositories.ProductImageRepository;
import shop.plant.shop.service.ProductImageService;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductImageService interface for managing product images.
 */
@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductService productService;

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

    /**
     * Converts a ProductImage entity to a ProductImageDto.
     *
     * @param productImage The ProductImage entity to convert.
     * @return A ProductImageDto with properties populated from the ProductImage entity.
     */
    @Override
    public ProductImageDto convertToDto(ProductImage productImage) {
        // Create a new ProductImageDto and populate its properties from the ProductImage entity
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setId(productImage.getId());
        productImageDto.setImageUrl(productImage.getImageUrl());

        // Set the product ID in the ProductImageDto
        productImageDto.setProductId(productImage.getProduct().getId());

        return productImageDto;
    }

    /**
     * Converts a ProductImageDto to a ProductImage entity.
     *
     * @param productImageDto The ProductImageDto to convert.
     * @return A ProductImage entity with properties populated from the ProductImageDto.
     */
    @Override
    public ProductImage convertToEntity(ProductImageDto productImageDto) {
        // Create a new ProductImage entity and populate its properties from the ProductImageDto
        ProductImage productImage = new ProductImage();
        productImage.setId(productImageDto.getId());
        productImage.setImageUrl(productImageDto.getImageUrl());

        // You may need to fetch the associated Product entity by its ID and set it in the ProductImage
        productImage.setProduct(productService.getProductById(productImageDto.getProductId()).orElse(null));

        return productImage;
    }

}
