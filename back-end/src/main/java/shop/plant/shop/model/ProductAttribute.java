package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents product attributes for a specific product.
 * It is not mapped to a database table on its own, but it contains information about product attributes, including:
 * - Unique identifier (id)
 * - Size of the product (size)
 * - Color of the product (color)
 * - Product ID (productId) - Foreign key linking to Product
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String color;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

}
