package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.OrderDetails;
import shop.plant.shop.repositories.OrderDetailsRepository;
import shop.plant.shop.service.OrderDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderDetailsService interface for managing order details.
 */
@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    /**
     * The repository for managing order details. It is automatically injected
     * by Spring's constructor-based dependency injection.
     */
    private final OrderDetailsRepository orderDetailsRepository;

    /**
     * Save a new order details or update an existing one.
     *
     * @param orderDetails The OrderDetails object to be saved or updated.
     * @return The saved OrderDetails object.
     */
    @Override
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    /**
     * Retrieve order details by its unique identifier (ID).
     *
     * @param id The ID of the order details to retrieve.
     * @return An Optional containing the OrderDetails object if found, or empty if not found.
     */
    @Override
    public Optional<OrderDetails> getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    /**
     * Retrieve all order details.
     *
     * @return A list of OrderDetails objects representing all order details.
     */
    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    /**
     * Update existing order details.
     *
     * @param orderDetails The OrderDetails object to be updated.
     */
    @Override
    public void updateOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    /**
     * Delete order details by its unique identifier (ID).
     *
     * @param id The ID of the order details to delete.
     */
    @Override
    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    // You can implement additional methods as needed for order details management.
}
