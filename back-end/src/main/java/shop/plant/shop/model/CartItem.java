package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a cart item entity.
 * It is mapped to a database table and contains information about items in the shopping cart, including:
 * - Unique identifier (id)
 * - Session ID (sessionId) - Foreign key linking to ShoppingSession
 * - Product ID (productId) - Foreign key linking to Product
 * - Quantity of the item in the cart (quantity)
 * - Timestamp for when the cart item was created (createAt)
 * - Relationships with ShoppingSession and Product entities
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "sessionId", referencedColumnName = "id")
    private ShoppingSession session;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

}
