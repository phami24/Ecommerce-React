package shop.plant.shop.exception;

/*
 * This class represents a custom exception for cart item-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to cart items.
 */
public class CartItemException extends Exception {
    public CartItemException(String message) {
        super(message);
    }
}
