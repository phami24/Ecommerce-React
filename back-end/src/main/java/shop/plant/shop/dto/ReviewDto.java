package shop.plant.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class represents a DTO for the Review entity.
 */
@Data
public class ReviewDto {
    private Long id;
    private String review;
    private Long productId;
    private Long userId;
    private LocalDateTime createdAt;
}
