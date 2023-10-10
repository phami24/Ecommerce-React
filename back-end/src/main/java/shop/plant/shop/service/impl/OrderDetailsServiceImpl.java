package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.OrderDetails;
import shop.plant.shop.model.OrderItems;
import shop.plant.shop.model.Product;
import shop.plant.shop.repositories.OrderDetailsRepository;
import shop.plant.shop.service.OrderDetailsService;
import shop.plant.shop.service.OrderItemsService;
import shop.plant.shop.service.ProductService;

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

    private final OrderItemsService orderItemsService;

    private final ProductService productService;

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

    /**
     * Calculate the total sum of an order details by its ID.
     *
     * @param id The ID of the order details to calculate the total for.
     * @return The calculated total or -1.0 if not found.
     */
    @Override
    public double calTotal(Long id) {
        Optional<OrderDetails> orderDetailsOptional = getOrderDetailsById(id);

        if (orderDetailsOptional.isPresent()) {
            OrderDetails orderDetails = orderDetailsOptional.get();

            // Fetch related OrderItems for the given OrderDetails
            List<OrderItems> orderItemsList = orderItemsService.getOrderItemsByOrderDetailId(orderDetails.getId());

            double total = 0.0;
            for (OrderItems orderItem : orderItemsList) {
                // Calculate the total for each order item (quantity * product price)
                Optional<Product> productOptional = productService.getProductById(orderItem.getProductId());
                Product product = productOptional.orElse(null);
                if (product != null) {
                    double itemTotal = orderItem.getQuantity() * product.getPrice();
                    total += itemTotal;
                }
            }

            return total;
        } else {
            // Return a negative value to indicate that the order details were not found
            return -1.0;
        }
    }

}
