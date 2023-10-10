package shop.plant.shop.dto;

import lombok.Data;

@Data
public class RatingDto {
    private Long id;
    private Long userId;
    private Long productId;
    private double rating;
}
