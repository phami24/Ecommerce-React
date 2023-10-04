package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.ProductAttribute;
import shop.plant.shop.repositories.ProductAttributeRepository;
import shop.plant.shop.service.ProductAttributeService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductAttributeService interface for managing product attributes.
 */
@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public ProductAttributeServiceImpl(ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    /**
     * Save a new product attribute or update an existing one.
     *
     * @param productAttribute The ProductAttribute object to be saved or updated.
     * @return The saved ProductAttribute object.
     */
    @Override
    public ProductAttribute saveProductAttribute(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    /**
     * Retrieve a product attribute by its unique identifier (ID).
     *
     * @param id The ID of the product attribute to retrieve.
     * @return An Optional containing the ProductAttribute object if found, or empty if not found.
     */
    @Override
    public Optional<ProductAttribute> getProductAttributeById(Long id) {
        return productAttributeRepository.findById(id);
    }

    /**
     * Retrieve all product attributes.
     *
     * @return A list of ProductAttribute objects representing all product attributes.
     */
    @Override
    public List<ProductAttribute> getAllProductAttributes() {
        return productAttributeRepository.findAll();
    }

    /**
     * Update an existing product attribute.
     *
     * @param productAttribute The ProductAttribute object to be updated.
     */
    @Override
    public void updateProductAttribute(ProductAttribute productAttribute) {
        productAttributeRepository.save(productAttribute);
    }

    /**
     * Delete a product attribute by its unique identifier (ID).
     *
     * @param id The ID of the product attribute to delete.
     */
    @Override
    public void deleteProductAttribute(Long id) {
        productAttributeRepository.deleteById(id);
    }

    // You can implement additional methods as needed for product attribute management.
}
