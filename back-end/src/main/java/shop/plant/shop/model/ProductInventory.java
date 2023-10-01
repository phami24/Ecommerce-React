package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a product inventory entity.
 * It is mapped to a database table and contains information about product inventory, including:
 * - Unique identifier (id)
 * - Quantity of the product available in inventory (quantity)
 */


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
}
