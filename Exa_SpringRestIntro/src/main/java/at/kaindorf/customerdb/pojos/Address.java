package at.kaindorf.customerdb.pojos;

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
    private Long addressId;

    @Column(length = 100)
    @NonNull
    private String streetName;

    @Column(nullable = false)
    @NonNull
    private Integer streetNumber;

    @Column(length = 50)
    @NonNull
    private String postalCode;

    @Column(length = 100)
    @NonNull
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country")
    @NonNull
    @ToString.Exclude
    private Country country;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Customer> customers = new ArrayList<>();
}
