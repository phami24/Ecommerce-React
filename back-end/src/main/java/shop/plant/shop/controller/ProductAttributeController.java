package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.ProductAttributeDto;
import shop.plant.shop.model.ProductAttribute;
import shop.plant.shop.service.ProductAttributeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-attributes")
@RequiredArgsConstructor
public class ProductAttributeController {
    private final ProductAttributeService productAttributeService;

    /**
     * Create a new product attribute.
     *
     * @param productAttributeDto The ProductAttributeDto representing the product attribute to create.
     * @return A ResponseEntity with the created ProductAttributeDto and a status code of 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<ProductAttributeDto> createProductAttribute(@RequestBody ProductAttributeDto productAttributeDto) {
        // Convert ProductAttributeDto to ProductAttribute entity
        ProductAttribute productAttribute = productAttributeService.convertToEntity(productAttributeDto);
        ProductAttribute savedProductAttribute = productAttributeService.saveProductAttribute(productAttribute);

        // Convert the saved ProductAttribute back to a ProductAttributeDto
        ProductAttributeDto savedProductAttributeDto = productAttributeService.convertToDto(savedProductAttribute);

        return new ResponseEntity<>(savedProductAttributeDto, HttpStatus.CREATED);
    }

    /**
     * Retrieve a product attribute by its ID.
     *
     * @param id The ID of the product attribute to retrieve.
     * @return A ResponseEntity with the retrieved ProductAttributeDto and a status code of 200 (OK) if found,
     * or a status code of 404 (NOT FOUND) if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeDto> getProductAttributeById(@PathVariable Long id) {
        Optional<ProductAttribute> optionalProductAttribute = productAttributeService.getProductAttributeById(id);

        if (optionalProductAttribute.isPresent()) {
            ProductAttributeDto productAttributeDto = productAttributeService.convertToDto(optionalProductAttribute.get());
            return new ResponseEntity<>(productAttributeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve all product attributes.
     *
     * @return A ResponseEntity with a list of ProductAttributeDtos representing all product attributes and a status code of 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProductAttributeDto>> getAllProductAttributes() {
        List<ProductAttribute> productAttributes = productAttributeService.getAllProductAttributes();

        // Convert the list of ProductAttribute entities to a list of ProductAttributeDtos
        List<ProductAttributeDto> productAttributeDtos = productAttributes.stream()
                .map(productAttributeService::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(productAttributeDtos, HttpStatus.OK);
    }

    /**
     * Update an existing product attribute.
     *
     * @param id                  The ID of the product attribute to update.
     * @param productAttributeDto The ProductAttributeDto representing the updated product attribute information.
     * @return A ResponseEntity with the updated ProductAttributeDto and a status code of 200 (OK) if updated,
     * or a status code of 404 (NOT FOUND) if the product attribute with the given ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeDto> updateProductAttribute(@PathVariable Long id, @RequestBody ProductAttributeDto productAttributeDto) {
        // Check if the product attribute with the given ID exists
        if (!productAttributeService.getProductAttributeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Convert ProductAttributeDto to ProductAttribute entity
        ProductAttribute productAttribute = productAttributeService.convertToEntity(productAttributeDto);
        productAttribute.setId(id);

        ProductAttribute updatedProductAttribute = productAttributeService.saveProductAttribute(productAttribute);

        // Convert the updated ProductAttribute back to a ProductAttributeDto
        ProductAttributeDto updatedProductAttributeDto = productAttributeService.convertToDto(updatedProductAttribute);

        return new ResponseEntity<>(updatedProductAttributeDto, HttpStatus.OK);
    }

    /**
     * Delete a product attribute by its ID.
     *
     * @param id The ID of the product attribute to delete.
     * @return A ResponseEntity with a status code of 204 (NO CONTENT) if deleted,
     * or a status code of 404 (NOT FOUND) if the product attribute with the given ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductAttribute(@PathVariable Long id) {
        // Check if the product attribute with the given ID exists
        if (!productAttributeService.getProductAttributeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productAttributeService.deleteProductAttribute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
