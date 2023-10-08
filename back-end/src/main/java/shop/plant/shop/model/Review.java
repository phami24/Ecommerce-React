package shop.plant.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a Review entity.
 * It is mapped to a database table and contains information about product reviews, including:
 * - Unique identifier (id)
 * - The text content of the review (review)
 * - The product being reviewed (product)
 * - The user who wrote the review (users)
 * - Timestamp for when the review was created (createdAt)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    private LocalDateTime createdAt;
}
