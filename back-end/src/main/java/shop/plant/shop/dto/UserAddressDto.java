package shop.plant.shop.dto;

import lombok.Data;

@Data
public class UserAddressDto {
    private Long id;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String postalCode;
    private String country;
    private Long userId; // Representing the user's ID associated with the address
}
