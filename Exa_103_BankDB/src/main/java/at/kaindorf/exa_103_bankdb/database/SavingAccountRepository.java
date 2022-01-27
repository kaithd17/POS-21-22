package at.kaindorf.exa_103_bankdb.database;

import at.kaindorf.exa_103_bankdb.pojos.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {
    List<SavingAccount> getSavingsAccounts(Long customerId);
}