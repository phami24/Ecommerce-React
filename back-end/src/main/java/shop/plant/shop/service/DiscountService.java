package shop.plant.shop.service;

import shop.plant.shop.model.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountService {
    /**
     * Save a new discount or update an existing one.
     *
     * @param discount The Discount object to be saved or updated.
     * @return The saved Discount object.
     */
    Discount saveDiscount(Discount discount);

    /**
     * Retrieve a discount by its unique identifier (ID).
     *
     * @param id The ID of the discount to retrieve.
     * @return An Optional containing the Discount object if found, or empty if not found.
     */
    Optional<Discount> getDiscountById(Long id);

    /**
     * Retrieve all active discounts.
     *
     * @return A list of active Discount objects.
     */
    List<Discount> getAllActiveDiscounts();

    /**
     * Deactivate a discount by setting its 'active' status to false.
     *
     * @param id The ID of the discount to deactivate.
     */
    void deactivateDiscount(Long id);

    /**
     * Activate a discount by setting its 'active' status to true.
     *
     * @param id The ID of the discount to activate.
     */
    void activateDiscount(Long id);
}
