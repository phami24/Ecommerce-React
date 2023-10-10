package shop.plant.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShoppingSessionDto {
    private Long id;
    private double total;
    private LocalDateTime createdAt;
    private String status;
    private Long userId; // User ID associated with the shopping session

    // Getters and setters for the fields
    // You can generate these using your IDE or manually define them
}
