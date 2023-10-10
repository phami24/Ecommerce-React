package shop.plant.shop.dto;

import lombok.Data;

@Data
public class ProductImageDto {
    private Long id;
    private String imageUrl;
    private Long productId; // Foreign key linking to Product
}
