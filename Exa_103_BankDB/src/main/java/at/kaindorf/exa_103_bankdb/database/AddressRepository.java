package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}