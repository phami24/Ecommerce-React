package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.ProductImage;

/**
 * This interface defines a repository for ProductImage objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
