package shop.plant.shop.exception;

/*
 * This class represents a custom exception for product-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to products.
 */
public class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }
}
