package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.service.ProductInventoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryService productInventoryService;

    /**
     * Get the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id The ID of the product inventory to retrieve.
     * @return A ResponseEntity containing the quantity if found, or an error message if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuantityByProductId(@PathVariable Long id) {
        Optional<Integer> quantityOptional = productInventoryService.getQuantityByProductId(id);

        if (quantityOptional.isPresent()) {
            return new ResponseEntity<>(quantityOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product inventory not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update the quantity of a product available in inventory by its unique identifier (ID).
     *
     * @param id       The ID of the product inventory to update.
     * @param quantity The new quantity to set in the product inventory.
     * @return A ResponseEntity indicating success or failure.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuantityByProductId(@PathVariable Long id, @RequestParam int quantity) {
        try {
            productInventoryService.updateQuantityByProductId(id, quantity);
            return new ResponseEntity<>("Product inventory updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update product inventory", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
