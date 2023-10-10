package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.OrderDetailsDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.*;
import shop.plant.shop.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    /**
     * Service for managing order details, including creation, retrieval, and deletion.
     */
    private final OrderDetailsService orderDetailsService;

    /**
     * Service for managing user-related operations.
     */
    private final UsersService usersService;

    /**
     * Service for managing payment details, including retrieval by ID.
     */
    private final PaymentDetailService paymentDetailService;


    /**
     * Endpoint to create a new order details.
     *
     * @param orderDetailsDto The OrderDetailsDto containing order details.
     * @return A ResponseEntity containing the created OrderDetails entity or an error response.
     * @throws UserException If there is an issue with user-related data.
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDetailsDto> createOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) throws UserException {
        OrderDetails orderDetails = convertToEntity(orderDetailsDto);

        OrderDetails savedOrderDetails = orderDetailsService.saveOrderDetails(orderDetails);
        OrderDetailsDto savedOrderDetailsDto = convertToDto(savedOrderDetails);
        return new ResponseEntity<>(savedOrderDetailsDto, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get order details by its ID.
     *
     * @param id The ID of the order details to retrieve.
     * @return A ResponseEntity containing the requested OrderDetails entity or a not found error response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsById(id);
        return orderDetails.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to get all order details.
     *
     * @return A ResponseEntity containing a list of OrderDetails entities or an error response if there are no order details.
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetailsList, HttpStatus.OK);
    }

    /**
     * Endpoint to update order details.
     *
     * @param orderDetailsDto The updated OrderDetailsDto.
     * @return A ResponseEntity containing the updated OrderDetails entity or a not found error response.
     * @throws UserException If there is an issue with user-related data.
     */
    @PutMapping("/update")
    public ResponseEntity<OrderDetailsDto> updateOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) throws UserException {
        Optional<OrderDetails> orderDetailOptional = orderDetailsService.getOrderDetailsById(orderDetailsDto.getId());
        OrderDetails orderDetails = orderDetailOptional.orElse(null);

        if (orderDetails != null) {
            orderDetails.setTotal(orderDetailsService.calTotal(orderDetailsDto.getId()));
        }
        // Update the order details
        orderDetailsService.updateOrderDetails(orderDetails);
        OrderDetailsDto updatedOrderDetailsDto = convertToDto(orderDetails);
        return new ResponseEntity<>(updatedOrderDetailsDto, HttpStatus.OK);
    }

    /**
     * Endpoint to delete order details by its ID.
     *
     * @param id The ID of the order details to delete.
     * @return A ResponseEntity indicating success or a not found error response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Converts an OrderDetails entity to its corresponding DTO.
     *
     * @param orderDetails The OrderDetails entity to convert.
     * @return The OrderDetailsDto representing the entity's data.
     */
    private OrderDetailsDto convertToDto(OrderDetails orderDetails) {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setId(orderDetails.getId());
        orderDetailsDto.setTotal(orderDetails.getTotal());
        orderDetailsDto.setCreateAt(orderDetails.getCreateAt());
        orderDetailsDto.setModifiedAt(orderDetails.getModifieldAt());

        // Set user ID if the user is not null
        if (orderDetails.getUser() != null) {
            orderDetailsDto.setUserId(orderDetails.getUser().getId());
        }

        // Set payment detail ID if the payment detail is not null
        if (orderDetails.getPaymentDetail() != null) {
            orderDetailsDto.setPaymentDetailId(orderDetails.getPaymentDetail().getId());
        }

        return orderDetailsDto;
    }

    /**
     * Converts an OrderDetailsDto to its corresponding entity.
     *
     * @param orderDetailsDto The OrderDetailsDto to convert.
     * @return The OrderDetails entity representing the DTO's data.
     * @throws UserException If there is an issue with user-related data.
     */
    private OrderDetails convertToEntity(OrderDetailsDto orderDetailsDto) throws UserException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTotal(orderDetailsDto.getTotal());
        orderDetails.setCreateAt(LocalDateTime.now());

        // Set user ID
        if (orderDetailsDto.getUserId() != null) {
            Users user = usersService.findUserById(orderDetailsDto.getUserId());
            if (user != null) {
                orderDetails.setUser(user);
            } else {
                throw new UserException("User not found for ID: " + orderDetailsDto.getUserId());
            }
        }

        // Set payment detail ID
        if (orderDetailsDto.getPaymentDetailId() != null) {
            Optional<PaymentDetail> paymentDetail = paymentDetailService.getPaymentDetailById(orderDetailsDto.getPaymentDetailId());
            if (paymentDetail.isPresent()) {
                orderDetails.setPaymentDetail(paymentDetail.get());
            } else {
                throw new RuntimeException("Payment detail not found for ID: " + orderDetailsDto.getPaymentDetailId());
            }
        }

        return orderDetails;
    }
}
