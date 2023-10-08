package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.plant.shop.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
