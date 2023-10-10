package shop.plant.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.UserAddressDto;
import shop.plant.shop.model.UserAddress;
import shop.plant.shop.service.UserAddressService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-addresses")
public class UserAddressController {
    private final UserAddressService userAddressService;

    @Autowired
    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    // Endpoint to save or update a user address
    @PostMapping("/save")
    public ResponseEntity<UserAddressDto> saveUserAddress(@RequestBody UserAddressDto userAddressDto) {
        UserAddress userAddress = userAddressService.convertToEntity(userAddressDto);
        UserAddress savedUserAddress = userAddressService.saveUserAddress(userAddress);
        UserAddressDto savedUserAddressDto = userAddressService.convertToDto(savedUserAddress);
        return new ResponseEntity<>(savedUserAddressDto, HttpStatus.CREATED);
    }

    // Endpoint to retrieve a user address by its ID
    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDto> getUserAddressById(@PathVariable Long id) {
        Optional<UserAddress> userAddressOptional = userAddressService.getUserAddressById(id);
        return userAddressOptional.map(userAddress -> {
            UserAddressDto userAddressDto = userAddressService.convertToDto(userAddress);
            return new ResponseEntity<>(userAddressDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to retrieve all user addresses for a specific user by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAddressDto>> getUserAddressesByUserId(@PathVariable Long userId) {
        List<UserAddress> userAddresses = userAddressService.getUserAddressesByUserId(userId);
        List<UserAddressDto> userAddressDtos = userAddresses.stream()
                .map(userAddressService::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userAddressDtos, HttpStatus.OK);
    }

    // Endpoint to update an existing user address
    @PutMapping("/update")
    public ResponseEntity<UserAddressDto> updateUserAddress(@RequestBody UserAddressDto userAddressDto) {
        UserAddress userAddress = userAddressService.convertToEntity(userAddressDto);
        UserAddress updatedUserAddress = userAddressService.saveUserAddress(userAddress);
        UserAddressDto updatedUserAddressDto = userAddressService.convertToDto(updatedUserAddress);
        return new ResponseEntity<>(updatedUserAddressDto, HttpStatus.OK);
    }

    // Endpoint to delete a user address by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id) {
        userAddressService.deleteUserAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
