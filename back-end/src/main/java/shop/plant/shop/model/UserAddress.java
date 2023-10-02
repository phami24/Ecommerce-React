package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a user's address entity.
 * It is mapped to a database table and contains information about a user's address, including:
 * - Unique identifier (id)
 * - User ID (userId) - Foreign key linking to Users
 * - Address line one (addressLineOne)
 * - Address line two (addressLineTwo)
 * - City (city)
 * - Postal code (postalCode)
 * - Country (country)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users user;
}
