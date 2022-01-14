package at.kaindorf.customerdb.data;

import at.kaindorf.customerdb.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}