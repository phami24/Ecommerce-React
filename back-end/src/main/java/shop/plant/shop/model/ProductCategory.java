package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a product category entity.
 * It is mapped to a database table and contains information about product categories, including:
 * - Unique identifier (id)
 * - Name of the category (name)
 * - Description or details of the category (desc)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
