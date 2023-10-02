package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.OrderDetails;

/**
 * This interface defines a repository for OrderDetails objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
