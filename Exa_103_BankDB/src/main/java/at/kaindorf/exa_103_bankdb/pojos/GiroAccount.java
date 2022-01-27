package at.kaindorf.exa_103_bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("GIRO")
@NamedQueries({
        @NamedQuery(name = "GiroAccount.getGiroAccounts", query = "SELECT g FROM GiroAccount g JOIN g.customerList cl WHERE cl.customerId = :customerId")
})
public class GiroAccount extends Account {
    @Column(name = "overdraft")
    private Double overdraft;

    @Column(name = "debit_interest")
    private Float debitInterest;

    @Column(name = "credit_interest")
    private Float creditInterest;
}
