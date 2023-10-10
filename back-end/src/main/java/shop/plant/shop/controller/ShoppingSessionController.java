package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.ShoppingSessionDto;
import shop.plant.shop.model.ShoppingSession;
import shop.plant.shop.service.ShoppingSessionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shopping-sessions")
@RequiredArgsConstructor
public class ShoppingSessionController {

    private final ShoppingSessionService shoppingSessionService;

    /**
     * Create a new shopping session.
     *
     * @param shoppingSessionDto The ShoppingSessionDto representing the new shopping session.
     * @return A ResponseEntity containing the created ShoppingSessionDto and a status code.
     */
    @PostMapping
    public ResponseEntity<ShoppingSessionDto> createShoppingSession(@RequestBody ShoppingSessionDto shoppingSessionDto) {
        try {
            // Convert the DTO to an entity
            ShoppingSession shoppingSession = shoppingSessionService.convertToEntity(shoppingSessionDto);

            // Save the shopping session
            ShoppingSession createdShoppingSession = shoppingSessionService.saveShoppingSession(shoppingSession);

            // Convert the created entity back to a DTO
            ShoppingSessionDto createdShoppingSessionDto = shoppingSessionService.convertToDto(createdShoppingSession);

            return new ResponseEntity<>(createdShoppingSessionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get a shopping session by its ID.
     *
     * @param id The ID of the shopping session to retrieve.
     * @return A ResponseEntity containing the retrieved ShoppingSessionDto and a status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingSessionDto> getShoppingSessionById(@PathVariable Long id) {
        Optional<ShoppingSession> shoppingSessionOptional = shoppingSessionService.getShoppingSessionById(id);

        if (shoppingSessionOptional.isPresent()) {
            // Convert the entity to a DTO
            ShoppingSessionDto shoppingSessionDto = shoppingSessionService.convertToDto(shoppingSessionOptional.get());
            return new ResponseEntity<>(shoppingSessionDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all shopping sessions.
     *
     * @return A ResponseEntity containing a list of ShoppingSessionDto objects and a status code.
     */
    @GetMapping
    public ResponseEntity<List<ShoppingSessionDto>> getAllShoppingSessions() {
        List<ShoppingSession> shoppingSessions = shoppingSessionService.getAllShoppingSessions();

        // Convert the list of entities to a list of DTOs
        List<ShoppingSessionDto> shoppingSessionDtos = shoppingSessionService.convertToDtoList(shoppingSessions);

        return new ResponseEntity<>(shoppingSessionDtos, HttpStatus.OK);
    }

    /**
     * Update an existing shopping session by its ID.
     *
     * @param id                 The ID of the shopping session to update.
     * @param shoppingSessionDto The ShoppingSessionDto representing the updated shopping session.
     * @return A ResponseEntity indicating success or failure.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShoppingSession(@PathVariable Long id, @RequestBody ShoppingSessionDto shoppingSessionDto) {
        try {
            // Check if the shopping session with the given ID exists
            if (!shoppingSessionService.getShoppingSessionById(id).isPresent()) {
                return new ResponseEntity<>("Shopping session not found", HttpStatus.NOT_FOUND);
            }

            // Convert the DTO to an entity
            ShoppingSession shoppingSession = shoppingSessionService.convertToEntity(shoppingSessionDto);

            // Set the ID to ensure updating the correct shopping session
            shoppingSession.setId(id);

            // Update the shopping session
            shoppingSessionService.updateShoppingSession(shoppingSession);

            return new ResponseEntity<>("Shopping session updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update shopping session", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a shopping session by its ID.
     *
     * @param id The ID of the shopping session to delete.
     * @return A ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShoppingSession(@PathVariable Long id) {
        try {
            // Check if the shopping session with the given ID exists
            if (!shoppingSessionService.getShoppingSessionById(id).isPresent()) {
                return new ResponseEntity<>("Shopping session not found", HttpStatus.NOT_FOUND);
            }

            // Delete the shopping session
            shoppingSessionService.deleteShoppingSession(id);

            return new ResponseEntity<>("Shopping session deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete shopping session", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
