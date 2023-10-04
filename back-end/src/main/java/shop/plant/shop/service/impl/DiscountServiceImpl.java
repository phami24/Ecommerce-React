package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.Discount;
import shop.plant.shop.repositories.DiscountRepository;
import shop.plant.shop.service.DiscountService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the DiscountService interface for managing discounts.
 */
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    /**
     * The repository for managing discounts. It is automatically injected
     * by Spring's constructor-based dependency injection.
     */
    private final DiscountRepository discountRepository;
    
    /**
     * Save a new discount or update an existing one.
     *
     * @param discount The Discount object to be saved or updated.
     * @return The saved Discount object.
     */
    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    /**
     * Retrieve a discount by its unique identifier (ID).
     *
     * @param id The ID of the discount to retrieve.
     * @return An Optional containing the Discount object if found, or empty if not found.
     */
    @Override
    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    /**
     * Retrieve all active discounts.
     *
     * @return A list of active Discount objects.
     */
    @Override
    public List<Discount> getAllActiveDiscounts() {
        return discountRepository.findByActiveTrueAndEndTimeAfter(LocalDateTime.now());
    }

    /**
     * Deactivate a discount by setting its 'active' status to false.
     *
     * @param id The ID of the discount to deactivate.
     */
    @Override
    public void deactivateDiscount(Long id) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        optionalDiscount.ifPresent(discount -> {
            discount.setActive(false);
            discountRepository.save(discount);
        });
    }

    /**
     * Activate a discount by setting its 'active' status to true.
     *
     * @param id The ID of the discount to activate.
     */
    @Override
    public void activateDiscount(Long id) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);
        optionalDiscount.ifPresent(discount -> {
            discount.setActive(true);
            discountRepository.save(discount);
        });
    }


}
