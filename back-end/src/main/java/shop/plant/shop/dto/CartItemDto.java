package shop.plant.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * This class represents a Data Transfer Object (DTO) for a cart item.
 * It is used for transferring cart item data between different parts of the application.
 * The DTO includes information about items in the shopping cart, such as:
 * - Unique identifier (id)
 * - Session ID (sessionId) - Typically linked to a ShoppingSession
 * - Product ID (productId) - Typically linked to a Product
 * - Quantity of the item in the cart (quantity)
 * - Timestamp for when the cart item was created (createdAt)
 * - Relationships with other entities, like ShoppingSession and Product
 */
@Data
public class CartItemDto {
    private Long id;
    private int quantity;
    private LocalDateTime createdAt;
    private Long sessionId;
    private Long productId;
}
