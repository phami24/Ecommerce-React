package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.Discount;

/**
 * This interface defines a repository for Discount objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
