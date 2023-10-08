package shop.plant.shop.exception;

/*
 * This class represents a custom exception for order items-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to order items.
 */
public class OrderItemsException extends Exception {
    public OrderItemsException(String message) {
        super(message);
    }
}
