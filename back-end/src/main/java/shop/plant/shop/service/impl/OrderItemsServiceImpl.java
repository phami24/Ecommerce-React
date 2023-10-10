package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.OrderItemsDto;
import shop.plant.shop.model.OrderItems;
import shop.plant.shop.repositories.OrderItemsRepository;
import shop.plant.shop.service.OrderItemsService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderItemsService interface for managing order items.
 */
@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {
    /**
     * The repository for managing order items. It is automatically injected
     * by Spring's constructor-based dependency injection.
     */
    private final OrderItemsRepository orderItemsRepository;

    /**
     * Save a new order item or update an existing one.
     *
     * @param orderItem The OrderItems object to be saved or updated.
     * @return The saved OrderItems object.
     */
    @Override
    public OrderItems saveOrderItem(OrderItems orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    /**
     * Retrieve order item by its unique identifier (ID).
     *
     * @param id The ID of the order item to retrieve.
     * @return An Optional containing the OrderItems object if found, or empty if not found.
     */
    @Override
    public Optional<OrderItems> getOrderItemById(Long id) {
        return orderItemsRepository.findById(id);
    }

    /**
     * Retrieve all order items.
     *
     * @return A list of OrderItems objects representing all order items.
     */
    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    /**
     * Update existing order item.
     *
     * @param orderItem The OrderItems object to be updated.
     */
    @Override
    public void updateOrderItem(OrderItems orderItem) {
        orderItemsRepository.save(orderItem);
    }

    /**
     * Delete order item by its unique identifier (ID).
     *
     * @param id The ID of the order item to delete.
     */
    @Override
    public void deleteOrderItem(Long id) {
        orderItemsRepository.deleteById(id);
    }

    @Override
    public OrderItemsDto convertToDto(OrderItems orderItem) {
        OrderItemsDto orderItemDto = new OrderItemsDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setCreateAt(orderItem.getCreateAt());
        return orderItemDto;
    }

    @Override
    public List<OrderItems> getOrderItemsByOrderDetailId(Long id) {
        return null;
    }

    // You can implement additional methods as needed for order item management.
}
