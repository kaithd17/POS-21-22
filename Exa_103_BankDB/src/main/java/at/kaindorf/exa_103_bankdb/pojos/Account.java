package at.kaindorf.exa_103_bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "Account.getBalance", query = "SELECT SUM(a.balance) FROM Account a JOIN a.customerList acl WHERE acl.customerId = :customerId")
})
public abstract class Account {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "balance")
    private Double balance;

    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customerList;
}
