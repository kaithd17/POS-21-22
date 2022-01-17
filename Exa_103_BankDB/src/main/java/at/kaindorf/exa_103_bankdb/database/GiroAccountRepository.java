package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.GiroAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiroAccountRepository extends JpaRepository<GiroAccount, Long> {
}