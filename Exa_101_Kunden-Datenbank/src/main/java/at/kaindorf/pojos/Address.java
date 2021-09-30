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

    @ManyToOne
    @JoinColumn(name = "country")
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    private List<Customer> customers = new ArrayList<>();
}
