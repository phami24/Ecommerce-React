package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.dto.CartItemDto;
import shop.plant.shop.model.CartItem;
import shop.plant.shop.service.CartItemService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    /**
     * Service for managing shopping cart items, including addition, retrieval, and removal.
     */
    private final CartItemService cartItemService;

    /**
     * Add a product to the shopping cart.
     *
     * @param cartItemDto The CartItemDto representing the product to add.
     * @return A ResponseEntity with a message indicating success or failure.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItemDto cartItemDto) {
        try {
            // Convert CartItemDto to CartItem entity and add to the cart
            cartItemService.addToCart(cartItemService.convertToEntity(cartItemDto));
            return ResponseEntity.ok("Product added to the cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the product to the cart.");
        }
    }

    /**
     * Endpoint to update the quantity of a cart item.
     *
     * @param cartItemDto The CartItemDto containing the updated quantity and cart item information.
     * @return A ResponseEntity with a success message or an error message.
     */
    @PostMapping("/update")
    public ResponseEntity<String> updateCartItemQuantity(@RequestBody CartItemDto cartItemDto) {
        try {
            // Convert CartItemDto to CartItem entity
            CartItem cartItem = cartItemService.convertToEntity(cartItemDto);

            // Call the service method to update the cart item's quantity
            cartItemService.updateCartItemQuantity(cartItem.getId(), cartItem.getQuantity());

            return ResponseEntity.ok("Cart item quantity updated successfully.");
        } catch (Exception e) {
            // Handle exceptions, such as if the cart item is not found or if there's an error during the update
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update cart item quantity.");
        }
    }


    /**
     * Remove a product from the shopping cart.
     *
     * @param cartItemDto The CartItemDto representing the product to remove.
     * @return A ResponseEntity with a message indicating success or failure.
     */
    @PostMapping("/remove")
    public ResponseEntity<String> removeCartItem(@RequestBody CartItemDto cartItemDto) {
        try {
            // Convert CartItemDto to CartItem entity and remove from the cart
            cartItemService.removeFromCart(cartItemService.convertToEntity(cartItemDto));
            return ResponseEntity.ok("Product removed from the cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove the product from the cart.");
        }
    }

    /**
     * View the contents of the shopping cart.
     *
     * @return A ResponseEntity containing a list of CartItemDto objects representing the cart items.
     */
    @GetMapping("/view")
    public ResponseEntity<Iterable<CartItemDto>> viewCart() {
        try {
            // Convert CartItem entities to CartItemDto objects and return as a list
            Iterable<CartItemDto> cartItemDtos = cartItemService.convertToDtos(cartItemService.getCartItems());
            return ResponseEntity.ok(cartItemDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
