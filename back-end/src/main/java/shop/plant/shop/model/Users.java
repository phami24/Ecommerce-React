package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents a user entity.
 * It is mapped to a database table and contains information about users, including:
 * - Unique identifier (id)
 * - First name (firstName)
 * - Last name (lastName)
 * - Password (password)
 * - Email (email)
 * - Role (role)
 * - Mobile number (mobile)
 * - Timestamp for when the user was created (createdAt)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private String mobile;
    private LocalDateTime createdAt = LocalDateTime.now();
}