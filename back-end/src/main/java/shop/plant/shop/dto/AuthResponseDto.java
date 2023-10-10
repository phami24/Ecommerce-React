package shop.plant.shop.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing an authentication response.
 * This DTO is used for transferring an access token and an optional message in the authentication process.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken; // Access token for authentication
    private String message;     // Optional message accompanying the authentication response

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
