package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.CartItem;
import shop.plant.shop.repositories.CartItemRepository;
import shop.plant.shop.service.CartItemService;

import java.util.List;

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
}
