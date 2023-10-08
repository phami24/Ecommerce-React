package shop.plant.shop.exception;

/**
 * This class represents a custom exception for product image-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to product images.
 */
public class ProductImageException extends Exception {
    public ProductImageException(String message) {
        super(message);
    }
}
