package shop.plant.shop.exception;

/**
 * This class represents a custom exception for shopping session-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to shopping sessions.
 */
public class ShoppingSessionException extends Exception {
    public ShoppingSessionException(String message) {
        super(message);
    }
}
