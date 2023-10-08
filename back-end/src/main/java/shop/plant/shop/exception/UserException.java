package shop.plant.shop.exception;

/*
 * This class represents a custom exception for user-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to users.
 */
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }
}