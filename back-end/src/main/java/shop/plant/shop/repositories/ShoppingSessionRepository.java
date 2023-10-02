package shop.plant.shop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.ShoppingSession;

/**
 * This interface defines a repository for ShoppingSession objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Long> {
}
