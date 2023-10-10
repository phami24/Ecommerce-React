package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents a Rating entity.
 * It is mapped to a database table and contains information about ratings, including:
 * - Unique identifier (id)
 * - User who submitted the rating (users)
 * - Product being rated (product)
 * - Rating value (rating)
 * - Timestamp for when the rating was created (createdAt)
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    private double rating;
    private LocalDateTime createdAt = LocalDateTime.now();
}
