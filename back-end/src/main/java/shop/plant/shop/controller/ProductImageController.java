package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.model.ProductImage;
import shop.plant.shop.dto.ProductImageDto;
import shop.plant.shop.service.ProductImageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    /**
     * Create a new product image.
     *
     * @param productImageDto The ProductImageDto object to be created.
     * @return A ResponseEntity with the created ProductImageDto object and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ProductImageDto> createProductImage(@RequestBody ProductImageDto productImageDto) {
        // Convert ProductImageDto to ProductImage entity
        ProductImage productImage = productImageService.convertToEntity(productImageDto);
        ProductImage savedProductImage = productImageService.saveProductImage(productImage);

        // Convert the saved ProductImage back to a ProductImageDto
        ProductImageDto savedProductImageDto = productImageService.convertToDto(savedProductImage);

        return new ResponseEntity<>(savedProductImageDto, HttpStatus.CREATED);
    }

    /**
     * Retrieve a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to retrieve.
     * @return A ResponseEntity with the ProductImageDto object if found, or a 404 status code if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductImageDto> getProductImageById(@PathVariable Long id) {
        Optional<ProductImage> optionalProductImage = productImageService.getProductImageById(id);

        return optionalProductImage.map(productImage -> {
            ProductImageDto productImageDto = productImageService.convertToDto(productImage);
            return new ResponseEntity<>(productImageDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieve all product images.
     *
     * @return A ResponseEntity with a list of ProductImageDto objects representing all product images.
     */
    @GetMapping
    public ResponseEntity<List<ProductImageDto>> getAllProductImages() {
        List<ProductImage> productImages = productImageService.getAllProductImages();

        // Convert the list of ProductImage entities to a list of ProductImageDtos
        List<ProductImageDto> productImageDtos = productImages.stream()
                .map(productImageService::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(productImageDtos, HttpStatus.OK);
    }

    /**
     * Update an existing product image.
     *
     * @param id               The ID of the product image to update.
     * @param productImageDto The updated ProductImageDto object.
     * @return A ResponseEntity with the updated ProductImageDto object and HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductImageDto> updateProductImage(@PathVariable Long id, @RequestBody ProductImageDto productImageDto) {
        // Check if the product image with the given ID exists
        if (!productImageService.getProductImageById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Convert ProductImageDto to ProductImage entity
        ProductImage productImage = productImageService.convertToEntity(productImageDto);
        productImage.setId(id);

        ProductImage updatedProductImage = productImageService.saveProductImage(productImage);

        // Convert the updated ProductImage back to a ProductImageDto
        ProductImageDto updatedProductImageDto = productImageService.convertToDto(updatedProductImage);

        return new ResponseEntity<>(updatedProductImageDto, HttpStatus.OK);
    }

    /**
     * Delete a product image by its unique identifier (ID).
     *
     * @param id The ID of the product image to delete.
     * @return A ResponseEntity with an HTTP status code indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long id) {
        // Check if the product image with the given ID exists
        if (!productImageService.getProductImageById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productImageService.deleteProductImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
