package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.pojos.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}