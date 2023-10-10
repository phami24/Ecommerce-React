package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.CartItemDto;
import shop.plant.shop.model.CartItem;
import shop.plant.shop.repositories.CartItemRepository;
import shop.plant.shop.service.CartItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CartItemService interface for managing shopping cart items.
 * This class provides methods to add, remove, and retrieve items from the shopping cart.
 */
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    /**
     * The repository for managing cart items. It is automatically injected
     * by Spring's constructor-based dependency injection.
     */
    private final CartItemRepository cartItemRepository;

    /**
     * Add a product to the shopping cart.
     *
     * @param cartItem The CartItem to add to the shopping cart.
     */
    @Override
    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    /**
     * Remove a product from the shopping cart.
     *
     * @param cartItem The CartItem to remove from the shopping cart.
     */
    @Override
    public void removeFromCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    /**
     * Get the list of items in the shopping cart.
     *
     * @return A list of CartItem objects representing the items in the shopping cart.
     */
    @Override
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    /**
     * Update the quantity of a product in the shopping cart.
     *
     * @param cartItemId  The ID of the cart item to update.
     * @param newQuantity The new quantity of the product in the cart.
     */
    @Override
    public void updateCartItemQuantity(Long cartItemId, int newQuantity) {
        // Attempt to find the CartItem with the specified cartItemId in the database
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        if (optionalCartItem.isPresent()) {
            // If the cart item is found, retrieve it from the Optional
            CartItem cartItem = optionalCartItem.get();

            // Update the quantity of the cart item with the new quantity provided
            cartItem.setQuantity(newQuantity);

            // Save the updated cart item back to the database
            cartItemRepository.save(cartItem);
        } else {
            // Handle the case where the cart item with the specified ID was not found.
            // You can throw an exception or handle it according to your application's logic.
            // For now, we'll print an error message.
            System.err.println("Cart item with ID " + cartItemId + " not found.");
        }
    }


    /**
     * Convert a CartItem entity to a CartItemDto.
     *
     * @param cartItem The CartItem entity to convert.
     * @return A CartItemDto representing the converted entity.
     */
    @Override
    public CartItemDto convertToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setCreatedAt(cartItem.getCreateAt());
        cartItemDto.setSessionId(cartItem.getSession().getId()); // Assuming there's a getId() method in ShoppingSession
        cartItemDto.setProductId(cartItem.getProduct().getId()); // Assuming there's a getId() method in Product
        return cartItemDto;
    }

    /**
     * Convert a CartItemDto to a CartItem entity.
     *
     * @param cartItemDto The CartItemDto to convert.
     * @return A CartItem entity representing the converted DTO.
     */
    @Override
    public CartItem convertToEntity(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setCreateAt(cartItemDto.getCreatedAt());
        // You may or may not need to set ShoppingSession and Product entities here.
        return cartItem;
    }

    /**
     * Convert a collection of CartItem entities to a list of CartItemDto objects.
     *
     * @param cartItems The collection of CartItem entities to convert.
     * @return A list of CartItemDto objects representing the converted entities.
     */
    @Override
    public List<CartItemDto> convertToDtos(Iterable<CartItem> cartItems) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemDtos.add(convertToDto(cartItem));
        }
        return cartItemDtos;
    }
}
