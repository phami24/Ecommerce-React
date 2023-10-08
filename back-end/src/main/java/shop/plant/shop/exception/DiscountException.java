package shop.plant.shop.exception;

/*
 * This class represents a custom exception for discount-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to discounts.
 */
public class DiscountException extends Exception {
    public DiscountException(String message) {
        super(message);
    }
}
