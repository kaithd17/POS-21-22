package at.kaindorf.pojos;

import at.kaindorf.json.JSONDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@JsonDeserialize(using = JSONDeserializer.class)
@NamedQueries({
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c) FROM Customer c"),
        @NamedQuery(name = "Customer.findFromCountry", query = "SELECT c FROM Customer c WHERE c.address.country.country_code = (:code)"),
        @NamedQuery(name = "Customer.findYears", query = "SELECT DISTINCT EXTRACT(YEAR from c.since) FROM Customer c")
})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long customer_id;

    @Column(length = 100)
    @NonNull
    private String firstname;

    @Column(length = 100)
    @NonNull
    private String lastname;

    @Column(length = 1)
    @NonNull
    private char gender;

    @NonNull
    private boolean active;

    @Column(length = 255)
    @NonNull
    private String email;

    @Column(name = "since")
    @NonNull
    private LocalDate since;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @NonNull
    @ToString.Exclude
    private Address address;
}
