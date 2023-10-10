package shop.plant.shop.dto;

import lombok.Data;

/**
 * This class represents a Data Transfer Object (DTO) for product information.
 * It is used to transfer product-related data between different parts of an application,
 * typically between the client and the server.
 */
@Data
public class ProductDto {
    // Unique identifier for the product
    private Long id;

    // The name of the product
    private String name;

    // The description or details of the product
    private String description;

    // The price of the product
    private double price;

    // The URL of the main image of the product
    private String mainImageUrl;

    // The gender for which the product is intended
    private String gender;

    // The ID of the product's category
    private Long categoryId;

    // The ID of the product's inventory
    private Long inventoryId;

    // The ID of the product's discount
    private Long discountId;
}
