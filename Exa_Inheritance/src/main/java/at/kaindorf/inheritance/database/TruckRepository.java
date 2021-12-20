package at.kaindorf.inheritance.database;

import at.kaindorf.inheritance.pojos.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}