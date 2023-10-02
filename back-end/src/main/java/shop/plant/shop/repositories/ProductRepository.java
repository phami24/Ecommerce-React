package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.Product;

/**
 * This interface defines a repository for Product objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
