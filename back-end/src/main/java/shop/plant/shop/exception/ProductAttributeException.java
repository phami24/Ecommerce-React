package shop.plant.shop.exception;

/*
 * This class represents a custom exception for product attribute-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to product attributes.
 */
public class ProductAttributeException extends Exception {
    public ProductAttributeException(String message) {
        super(message);
    }
}
