package shop.plant.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class represents a Data Transfer Object (DTO) for order items.
 * It is used to transfer data related to order items between different parts
 * of an application, typically between the client and the server.
 */
@Data
public class OrderItemsDto {
    // Unique identifier for the order item
    private Long id;

    // Identifier of the associated product
    private Long productId;

    // Quantity of the product in the order
    private int quantity;

    // Timestamp for when the order item was created
    private LocalDateTime createAt;

    // OrderId of order item
    private Long orderId;
}
