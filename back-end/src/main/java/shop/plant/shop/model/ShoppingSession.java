package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a shopping session entity.
 * It is mapped to a database table and contains information about shopping sessions, including:
 * - Unique identifier (id)
 * - User ID (userId) - Foreign key linking to User entity
 * - Total amount of the shopping session (total)
 * - Timestamp for when the shopping session was created (createdAt)
 * - Status of the shopping session (status)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users users;
}
