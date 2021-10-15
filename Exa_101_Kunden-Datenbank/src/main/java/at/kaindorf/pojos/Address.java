package at.kaindorf.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Address.countAll", query = "SELECT COUNT(address) FROM Address address")
})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long address_id;

    @Column(length = 100)
    @NonNull
    private String street_name;

    @Column(nullable = false)
    @NonNull
    private int street_number;

    @Column(length = 50)
    @NonNull
    private String postal_code;

    @Column(length = 100)
    @NonNull
    private String city;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "country")
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> customers = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%s - %d; %s; %s", street_name, street_number, postal_code, city);
    }
}
