package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.Product;
import shop.plant.shop.repositories.ProductRepository;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductService interface for managing products.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Save a new product or update an existing one.
     *
     * @param product The Product object to be saved or updated.
     * @return The saved Product object.
     */
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Retrieve a product by its unique identifier (ID).
     *
     * @param id The ID of the product to retrieve.
     * @return An Optional containing the Product object if found, or empty if not found.
     */
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Retrieve all products.
     *
     * @return A list of Product objects representing all products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Update an existing product.
     *
     * @param product The Product object to be updated.
     */
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Delete a product by its unique identifier (ID).
     *
     * @param id The ID of the product to delete.
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // You can implement additional methods as needed for product management.
}
