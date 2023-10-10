package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.ProductCategory;
import shop.plant.shop.repositories.ProductCategoryRepository;
import shop.plant.shop.service.ProductCategoryService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductCategoryService interface for managing product categories.
 */
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;


    /**
     * Save a new product category or update an existing one.
     *
     * @param productCategory The ProductCategory object to be saved or updated.
     * @return The saved ProductCategory object.
     */
    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    /**
     * Retrieve a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to retrieve.
     * @return An Optional containing the ProductCategory object if found, or empty if not found.
     */
    @Override
    public Optional<ProductCategory> getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id);
    }

    /**
     * Retrieve all product categories.
     *
     * @return A list of ProductCategory objects representing all product categories.
     */
    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    /**
     * Update an existing product category.
     *
     * @param productCategory The ProductCategory object to be updated.
     */
    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    /**
     * Delete a product category by its unique identifier (ID).
     *
     * @param id The ID of the product category to delete.
     */
    @Override
    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }

    // You can implement additional methods as needed for product category management.
}
