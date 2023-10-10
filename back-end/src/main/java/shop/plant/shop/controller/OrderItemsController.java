package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.OrderItemsDto;
import shop.plant.shop.model.OrderDetails;
import shop.plant.shop.model.OrderItems;
import shop.plant.shop.model.Product;
import shop.plant.shop.service.OrderDetailsService;
import shop.plant.shop.service.OrderItemsService;
import shop.plant.shop.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemsController {
    /**
     * The service responsible for managing order items, including creation, retrieval, and deletion.
     */
    private final OrderItemsService orderItemsService;

    /**
     * The service responsible for managing order details, including creation, retrieval, and deletion.
     */
    private final OrderDetailsService orderDetailsService;

    /**
     * The service responsible for managing products, including retrieval and other product-related operations.
     */
    private final ProductService productService;


    /**
     * Endpoint to create a new order item.
     *
     * @param orderItemsDto The OrderItemsDto containing order item details.
     * @return A ResponseEntity containing the created OrderItemsDto or an error response if the product or order details are not found.
     */
    @PostMapping
    public ResponseEntity<OrderItemsDto> createOrderItem(@RequestBody OrderItemsDto orderItemsDto) {
        // Check if the product exists in the database
        Optional<Product> productOptional = productService.getProductById(orderItemsDto.getProductId());
        Product product = productOptional.orElse(null);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if the order details exist in the database
        Optional<OrderDetails> orderDetailsOptional = orderDetailsService.getOrderDetailsById(orderItemsDto.getOrderId());
        OrderDetails orderDetails = orderDetailsOptional.orElse(null);

        if (orderDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create a new order item
        OrderItems orderItem = new OrderItems();
        orderItem.setProductId(orderItemsDto.getProductId());
        orderItem.setQuantity(orderItemsDto.getQuantity());
        orderItem.setOrderDetails(orderDetails);

        // Save the order item to the database
        OrderItems savedOrderItem = orderItemsService.saveOrderItem(orderItem);
        OrderItemsDto savedOrderItemDto = orderItemsService.convertToDto(savedOrderItem);

        return new ResponseEntity<>(savedOrderItemDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a specific order item by its ID.
     *
     * @param id The ID of the order item to retrieve.
     * @return A ResponseEntity containing the requested OrderItemsDto or a not found error response if the order item is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemsDto> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItems> optionalOrderItem = orderItemsService.getOrderItemById(id);
        if (optionalOrderItem.isPresent()) {
            OrderItems orderItem = optionalOrderItem.get();
            OrderItemsDto orderItemDto = orderItemsService.convertToDto(orderItem);
            return new ResponseEntity<>(orderItemDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to retrieve all order items.
     *
     * @return A ResponseEntity containing a list of OrderItemsDto or an error response if there are no order items.
     */
    @GetMapping
    public ResponseEntity<List<OrderItemsDto>> getAllOrderItems() {
        List<OrderItems> orderItems = orderItemsService.getAllOrderItems();
        List<OrderItemsDto> orderItemsDtoList = orderItems.stream()
                .map(orderItemsService::convertToDto)
                .collect(Collectors.toList());
        if (orderItemsDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orderItemsDtoList, HttpStatus.OK);
        }
    }

    /**
     * Endpoint to update an existing order item by its ID.
     *
     * @param id            The ID of the order item to update.
     * @param orderItemsDto The updated OrderItemsDto.
     * @return A ResponseEntity containing the updated OrderItemsDto or a not found error response if the order item is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemsDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemsDto orderItemsDto) {
        Optional<OrderItems> optionalOrderItem = orderItemsService.getOrderItemById(id);
        if (optionalOrderItem.isPresent()) {
            OrderItems orderItemToUpdate = optionalOrderItem.get();
            orderItemToUpdate.setProductId(orderItemsDto.getProductId());
            orderItemToUpdate.setQuantity(orderItemsDto.getQuantity());

            orderItemsService.saveOrderItem(orderItemToUpdate); // Update the existing order item

            OrderItemsDto updatedOrderItemDto = orderItemsService.convertToDto(orderItemToUpdate);
            return new ResponseEntity<>(updatedOrderItemDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to delete an order item by its ID.
     *
     * @param id The ID of the order item to delete.
     * @return A ResponseEntity indicating success or a not found error response if the order item is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        Optional<OrderItems> optionalOrderItem = orderItemsService.getOrderItemById(id);
        if (optionalOrderItem.isPresent()) {
            orderItemsService.deleteOrderItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
