package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.ProductCategory;

/**
 * This interface defines a repository for ProductCategory objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
