package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE UPPER(c.lastname) LIKE UPPER(CONCAT('%',:lastname, '%')) ORDER BY c.lastname, c.firstname")
    List<Customer> findCustomerByLastname(@Param("lastname") String lastname);

    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
    Customer findCustomerByCustomerId(@Param("customerId") Long customerId);
}