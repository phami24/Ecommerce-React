package shop.plant.shop.exception;

/**
 * This class represents a custom exception for user address-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to user addresses.
 */
public class UserAddressException extends Exception {
    public UserAddressException(String message) {
        super(message);
    }
}
