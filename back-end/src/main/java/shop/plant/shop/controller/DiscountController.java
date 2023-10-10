package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.model.Discount;
import shop.plant.shop.service.DiscountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    /**
     * Service for managing discounts, including creation, retrieval, and deletion.
     */
    private final DiscountService discountService;


    /**
     * Endpoint to save a new discount or update an existing one.
     *
     * @param discount The Discount object to be saved or updated.
     * @return A ResponseEntity with the saved Discount object or an error message.
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveDiscount(@RequestBody Discount discount) {
        try {
            Discount savedDiscount = discountService.saveDiscount(discount);
            return ResponseEntity.ok(savedDiscount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save discount.");
        }
    }

    /**
     * Endpoint to retrieve a discount by its unique identifier (ID).
     *
     * @param id The ID of the discount to retrieve.
     * @return A ResponseEntity containing the Discount object if found, or a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable Long id) {
        Optional<Discount> optionalDiscount = discountService.getDiscountById(id);
        if (optionalDiscount.isPresent()) {
            return ResponseEntity.ok(optionalDiscount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to retrieve all active discounts.
     *
     * @return A ResponseEntity containing a list of active Discount objects.
     */
    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveDiscounts() {
        List<Discount> activeDiscounts = discountService.getAllActiveDiscounts();
        return ResponseEntity.ok(activeDiscounts);
    }

    /**
     * Endpoint to deactivate a discount by setting its 'active' status to false.
     *
     * @param id The ID of the discount to deactivate.
     * @return A ResponseEntity indicating success or an error message.
     */
    @PostMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateDiscount(@PathVariable Long id) {
        try {
            discountService.deactivateDiscount(id);
            return ResponseEntity.ok("Discount deactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to deactivate discount.");
        }
    }

    /**
     * Endpoint to activate a discount by setting its 'active' status to true.
     *
     * @param id The ID of the discount to activate.
     * @return A ResponseEntity indicating success or an error message.
     */
    @PostMapping("/activate/{id}")
    public ResponseEntity<?> activateDiscount(@PathVariable Long id) {
        try {
            discountService.activateDiscount(id);
            return ResponseEntity.ok("Discount activated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to activate discount.");
        }
    }
}
