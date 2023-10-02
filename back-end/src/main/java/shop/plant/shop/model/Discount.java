package shop.plant.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a Discount entity.
 * It is mapped to a database table and contains information about discounts, including:
 * - Unique identifier (id)
 * - Name of the discount (name)
 * - Description or details of the discount (desc)
 * - Percentage value of the discount (discountPercent)
 * - Active status of the discount (active)
 * - Timestamp for when the discount was created (createdAt)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int discountPercent;
    private boolean active;
    private LocalDateTime createdAt = LocalDateTime.now();
}
