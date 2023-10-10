package shop.plant.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class OrderDetailsDto {
    private Long id;
    private double total;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long userId; // Assuming you want to expose user ID
    private Long paymentDetailId; // Assuming you want to expose payment detail ID
}
