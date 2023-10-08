package shop.plant.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.plant.shop.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}
