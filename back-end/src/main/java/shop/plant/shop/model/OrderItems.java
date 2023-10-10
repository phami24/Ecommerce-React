package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents an order item entity.
 * It is mapped to a database table and contains information about items in an order, including:
 * - Unique identifier (id)
 * - Order ID (orderId) - Foreign key linking to Order entity
 * - Product ID (productId) - Foreign key linking to Product entity
 * - Quantity of the product in the order (quantity)
 * - Timestamp for when the order item was created (createAt)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "oderId", referencedColumnName = "id")
    private OrderDetails orderDetails;

}
