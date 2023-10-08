package shop.plant.shop.exception;

/*
 * This class represents a custom exception for product category-related issues.
 * It extends the standard Exception class and provides a way to throw exceptions specific to product categories.
 */
public class ProductCategoryException extends Exception {
    public ProductCategoryException(String message) {
        super(message);
    }
}
