package shop.plant.shop.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing user registration information.
 * This DTO is used for transferring user email and password during the registration process.
 */
@Data
public class RegisterDto {
    private String email;    // User's email address
    private String password; // User's desired password
}
