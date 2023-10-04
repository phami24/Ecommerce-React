package shop.plant.shop.service;

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

    // You can add more comments for other methods related to cart item management...
}
