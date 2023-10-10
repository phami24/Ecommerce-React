import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.model.ProductCategory;
import shop.plant.shop.service.ProductCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    /**
     * Create a new product category.
     *
     * @param productCategory The ProductCategory object to be created.
     * @return A ResponseEntity with the created ProductCategory object and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory savedProductCategory = productCategoryService.saveProductCategory(productCategory);
        return new ResponseEntity<>(savedProductCategory, HttpStatus.CREATED);
    }

    /**
     * Retrieve a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to retrieve.
     * @return A ResponseEntity with the ProductCategory object if found, or a 404 status code if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) {
        Optional<ProductCategory> optionalProductCategory = productCategoryService.getProductCategoryById(id);

        return optionalProductCategory.map(productCategory ->
                        new ResponseEntity<>(productCategory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieve all product categories.
     *
     * @return A ResponseEntity with a list of ProductCategory objects representing all product categories.
     */
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    /**
     * Update an existing product category.
     *
     * @param id              The ID of the product category to update.
     * @param productCategory The updated ProductCategory object.
     * @return A ResponseEntity with the updated ProductCategory object and HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        // Check if the product category with the given ID exists
        if (!productCategoryService.getProductCategoryById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productCategory.setId(id);
        ProductCategory updatedProductCategory = productCategoryService.saveProductCategory(productCategory);

        return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
    }

    /**
     * Delete a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to delete.
     * @return A ResponseEntity with an HTTP status code indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        // Check if the product category with the given ID exists
        if (!productCategoryService.getProductCategoryById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
