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
@Entity(name = "country")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "country_id")
    @EqualsAndHashCode.Exclude
    private Long countryId;

    @Column(length = 50, name = "name")
    @NonNull
    private String country_name;

    @Column(length = 10, name = "code")
    @NonNull
    private String country_code;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NonNull
    @ToString.Exclude
    private List<Address> addresses = new ArrayList<>();
}
