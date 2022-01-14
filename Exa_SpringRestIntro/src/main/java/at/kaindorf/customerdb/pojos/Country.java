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
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "country_id")
    @EqualsAndHashCode.Exclude
    private Long countryId;

    @Column(length = 50, name = "name")
    @NonNull
    private String countryName;

    @Column(length = 10, name = "code")
    @NonNull
    private String countryCode;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Address> addresses = new ArrayList<>();
}
