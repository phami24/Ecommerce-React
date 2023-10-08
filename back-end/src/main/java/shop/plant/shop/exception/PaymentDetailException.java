package shop.plant.shop.exception;

/*
 * This class represents a custom exception for payment detail-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to payment details.
 */
public class PaymentDetailException extends Exception {
    public PaymentDetailException(String message) {
        super(message);
    }
}
