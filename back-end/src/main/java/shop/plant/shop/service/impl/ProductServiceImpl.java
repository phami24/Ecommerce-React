package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.ProductDto;
import shop.plant.shop.model.Discount;
import shop.plant.shop.model.Product;
import shop.plant.shop.model.ProductCategory;
import shop.plant.shop.model.ProductInventory;
import shop.plant.shop.repositories.ProductRepository;
import shop.plant.shop.service.DiscountService;
import shop.plant.shop.service.ProductCategoryService;
import shop.plant.shop.service.ProductInventoryService;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductService interface for managing products.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductInventoryService productInventoryService;
    private final DiscountService discountService;

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

    /**
     * Converts a Product entity to a ProductDto.
     *
     * @param product The Product entity to be converted.
     * @return A ProductDto.
     */
    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setMainImageUrl(product.getMainImageUrl());
        productDto.setGender(product.getGender());

        // Set related entities if not null.
        if (product.getProductCategory() != null) {
            productDto.setCategoryId(product.getProductCategory().getId());
        }

        if (product.getProductInventory() != null) {
            productDto.setInventoryId(product.getProductInventory().getId());
        }

        if (product.getDiscount() != null) {
            productDto.setDiscountId(product.getDiscount().getId());
        }

        return productDto;
    }

    /**
     * Converts a ProductDto to a Product entity.
     *
     * @param productDto The ProductDto to be converted.
     * @return A Product entity.
     */

    @Override
    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setMainImageUrl(productDto.getMainImageUrl());
        product.setGender(productDto.getGender());

        // Fetch related entities by their IDs if needed
        if (productDto.getCategoryId() != null) {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(productDto.getCategoryId())
                    .orElse(null);
            product.setProductCategory(productCategory);
        }

        if (productDto.getInventoryId() != null) {
            ProductInventory productInventory = productInventoryService.getInventoryById(productDto.getInventoryId())
                    .orElse(null);
            product.setProductInventory(productInventory);
        }

        if (productDto.getDiscountId() != null) {
            Discount discount = discountService.getDiscountById(productDto.getDiscountId())
                    .orElse(null);
            product.setDiscount(discount);
        }

        return product;
    }


}
