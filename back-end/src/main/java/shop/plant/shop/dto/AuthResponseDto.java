package shop.plant.shop.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String message;

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}