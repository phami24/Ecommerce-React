package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.model.Product;
import shop.plant.shop.dto.ProductDto;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Create a new product.
     *
     * @param productDto The ProductDto representing the product to create.
     * @return A ResponseEntity with the created ProductDto and a status code of 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        // Convert ProductDto to Product entity
        Product product = productService.convertToEntity(productDto);
        Product savedProduct = productService.saveProduct(product);

        // Convert the saved Product back to a ProductDto
        ProductDto savedProductDto = productService.convertToDto(savedProduct);

        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return A ResponseEntity with the retrieved ProductDto and a status code of 200 (OK) if found,
     * or a status code of 404 (NOT FOUND) if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            ProductDto productDto = productService.convertToDto(optionalProduct.get());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve all products.
     *
     * @return A ResponseEntity with a list of ProductDtos representing all products and a status code of 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        // Convert the list of Product entities to a list of ProductDtos
        List<ProductDto> productDtos = products.stream()
                .map(productService::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Update an existing product.
     *
     * @param id         The ID of the product to update.
     * @param productDto The ProductDto representing the updated product information.
     * @return A ResponseEntity with the updated ProductDto and a status code of 200 (OK) if updated,
     * or a status code of 404 (NOT FOUND) if the product with the given ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        // Check if the product with the given ID exists
        if (!productService.getProductById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Convert ProductDto to Product entity
        Product product = productService.convertToEntity(productDto);
        product.setId(id);

        Product updatedProduct = productService.saveProduct(product);

        // Convert the updated Product back to a ProductDto
        ProductDto updatedProductDto = productService.convertToDto(updatedProduct);

        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return A ResponseEntity with a status code of 204 (NO CONTENT) if deleted,
     * or a status code of 404 (NOT FOUND) if the product with the given ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Check if the product with the given ID exists
        if (!productService.getProductById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
