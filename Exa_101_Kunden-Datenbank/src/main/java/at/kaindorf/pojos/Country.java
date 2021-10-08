package at.kaindorf.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "country")
@NamedQueries({
        @NamedQuery(name = "Country.findByName", query = "SELECT c FROM country c WHERE UPPER(c.country_name) = UPPER(:name) "),
        @NamedQuery(name= "Country.findAll", query = "SELECT c FROM country c"),
        @NamedQuery(name = "Country.countAll", query = "SELECT COUNT(c) FROM country c")
})
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
    @EqualsAndHashCode.Exclude
    private List<Address> addresses = new ArrayList<>();
}
