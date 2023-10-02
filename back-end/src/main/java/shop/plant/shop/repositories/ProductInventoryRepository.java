package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.ProductInventory;

/**
 * This interface defines a repository for ProductInventory objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
