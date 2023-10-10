package shop.plant.shop.dto;

import lombok.Data;

/**
 * This class represents a Data Transfer Object (DTO) for product attributes.
 * It is used to transfer data related to product attributes between different parts
 * of an application, typically between the client and the server.
 */
@Data
public class ProductAttributeDto {
    // Unique identifier for the product attribute
    private Long id;

    // Size of the product
    private String size;

    // Color of the product
    private String color;

    // Product ID associated with this attribute
    private Long productId;
}
