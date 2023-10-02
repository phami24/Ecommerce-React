package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.OrderItems;

/**
 * This interface defines a repository for OrderItems objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
