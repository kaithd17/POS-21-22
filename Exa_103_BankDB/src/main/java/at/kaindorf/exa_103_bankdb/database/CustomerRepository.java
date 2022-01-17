package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}