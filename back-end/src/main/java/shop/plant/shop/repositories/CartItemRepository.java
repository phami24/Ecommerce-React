package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.CartItem;

/**
 * This interface defines a repository for CartItem objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
