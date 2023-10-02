package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.UserAddress;

/**
 * This interface defines a repository for UserAddress objects.
 * It extends JpaRepository, which provides basic database operations.
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
