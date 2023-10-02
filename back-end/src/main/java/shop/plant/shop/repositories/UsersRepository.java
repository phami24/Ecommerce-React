package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByEmail(String email);
}