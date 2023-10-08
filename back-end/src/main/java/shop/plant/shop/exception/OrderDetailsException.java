package shop.plant.shop.exception;

/*
 * This class represents a custom exception for order details-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to order details.
 */
public class OrderDetailsException extends Exception {
    public OrderDetailsException(String message) {
        super(message);
    }
}
