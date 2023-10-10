package shop.plant.shop.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing user login information.
 * This DTO is used for transferring user email and password during the login process.
 */
@Data
public class LoginDto {
    private String email;    // User's email address
    private String password; // User's password
}
