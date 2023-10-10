package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.ProductAttributeDto;
import shop.plant.shop.model.ProductAttribute;
import shop.plant.shop.repositories.ProductAttributeRepository;
import shop.plant.shop.service.ProductAttributeService;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductAttributeService interface for managing product attributes.
 */
@Service
@RequiredArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;
    private final ProductService productService;

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

    /**
     * Converts a ProductAttributeDto to a ProductAttribute entity.
     *
     * @param productAttributeDto The ProductAttributeDto to be converted.
     * @return A ProductAttribute entity.
     */
    @Override
    public ProductAttribute convertToEntity(ProductAttributeDto productAttributeDto) {
        if (productAttributeDto == null) {
            return null;
        }

        ProductAttribute productAttribute = new ProductAttribute();

        // Set the attributes of the ProductAttribute entity from the DTO
        productAttribute.setId(productAttributeDto.getId());
        productAttribute.setSize(productAttributeDto.getSize());
        productAttribute.setColor(productAttributeDto.getColor());

        // You may also set the product association here if needed:
        productAttribute.setProduct(productService.getProductById(productAttributeDto.getProductId()).orElse(null));

        return productAttribute;
    }

    /**
     * Converts a ProductAttribute entity to a ProductAttributeDto.
     *
     * @param savedProductAttribute The ProductAttribute entity to be converted.
     * @return A ProductAttributeDto.
     */
    @Override
    public ProductAttributeDto convertToDto(ProductAttribute savedProductAttribute) {
        if (savedProductAttribute == null) {
            return null;
        }

        ProductAttributeDto productAttributeDto = new ProductAttributeDto();

        // Set the attributes of the ProductAttributeDto from the entity
        productAttributeDto.setId(savedProductAttribute.getId());
        productAttributeDto.setSize(savedProductAttribute.getSize());
        productAttributeDto.setColor(savedProductAttribute.getColor());

        // You may also set the product ID in the DTO if needed:
        productAttributeDto.setProductId(savedProductAttribute.getProduct().getId());

        return productAttributeDto;
    }
}
