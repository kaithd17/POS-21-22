package at.kaindorf.exa_103_bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("SPAR")
@NamedQueries({
        @NamedQuery(name = "SavingAccount.getSavingsAccounts", query = "SELECT s FROM SavingAccount s JOIN s.customerList sl WHERE sl.customerId = :customerId")
})
public class SavingAccount extends Account {
    @Column(name = "interest")
    private Double interest;
}
