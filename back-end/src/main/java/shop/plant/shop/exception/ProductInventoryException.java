package shop.plant.shop.exception;

/**
 * This class represents a custom exception for product inventory-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to product inventory.
 */
public class ProductInventoryException extends Exception {
    public ProductInventoryException(String message) {
        super(message);
    }
}
