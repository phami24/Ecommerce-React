package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents order details for a transaction.
 * It is mapped to a database table and contains information about orders, including:
 * - Unique identifier (id)
 * - User ID (userId) - Foreign key linking to User entity
 * - Total amount of the order (total)
 * - Payment ID (paymentId) - Foreign key linking to PaymentDetail entity
 * - Timestamp for when the order was created (createAt)
 * - Timestamp for when the order was last modified (modifieldAt)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime modifieldAt;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "paymentId", referencedColumnName = "id")
    private PaymentDetail paymentDetail;
}
