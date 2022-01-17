package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}