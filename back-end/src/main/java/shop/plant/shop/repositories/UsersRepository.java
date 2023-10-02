package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.Users;

/**
 * This interface defines a repository for Users objects.
 * It extends JpaRepository, which provides basic database operations.
 * The @Repository annotation marks this interface as a Spring repository.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Find a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return The User object if found, or null if not found.
     */
    public Users findByEmail(String email);
}
