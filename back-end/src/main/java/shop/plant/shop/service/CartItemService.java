package shop.plant.shop.service;

import shop.plant.shop.dto.CartItemDto;
import shop.plant.shop.model.CartItem;

import java.util.List;

public interface CartItemService {
    /**
     * Add a product to the shopping cart.
     *
     * @param cartItem The CartItem to add to the shopping cart.
     */
    void addToCart(CartItem cartItem);

    /**
     * Remove a product from the shopping cart.
     *
     * @param cartItem The CartItem to remove from the shopping cart.
     */
    void removeFromCart(CartItem cartItem);

    /**
     * Get the list of items in the shopping cart.
     *
     * @return A list of CartItem objects representing the items in the shopping cart.
     */
    List<CartItem> getCartItems();

    /**
     * Update the quantity of a product in the shopping cart.
     *
     * @param cartItemId  The ID of the cart item to update.
     * @param newQuantity The new quantity of the product in the cart.
     */
    void updateCartItemQuantity(Long cartItemId, int newQuantity);

    /**
     * Convert a CartItem entity to a CartItemDto.
     *
     * @param cartItem The CartItem entity to convert.
     * @return A CartItemDto representing the converted entity.
     */
    CartItemDto convertToDto(CartItem cartItem);

    /**
     * Convert a CartItemDto to a CartItem entity.
     *
     * @param cartItemDto The CartItemDto to convert.
     * @return A CartItem entity representing the converted DTO.
     */
    CartItem convertToEntity(CartItemDto cartItemDto);

    /**
     * Convert a collection of CartItem entities to a list of CartItemDto objects.
     *
     * @param cartItems The collection of CartItem entities to convert.
     * @return A list of CartItemDto objects representing the converted entities.
     */
    List<CartItemDto> convertToDtos(Iterable<CartItem> cartItems);
}
