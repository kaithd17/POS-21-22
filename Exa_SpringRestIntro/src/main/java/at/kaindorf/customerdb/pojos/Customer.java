package at.kaindorf.customerdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Customer implements Serializable {
   // public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long customerId;

    @Column(length = 100)
    @NonNull
    private String firstname;

    @Column(length = 100)
    @NonNull
    private String lastname;

    @Column(length = 1)
    @NonNull
    private Character gender;

    @NonNull
    private Boolean active;

    @Column(length = 255)
    @NonNull
    private String email;

    @Column(name = "since")
    @NonNull
    private LocalDate since;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    @NonNull
    @ToString.Exclude
    @JsonIgnore
    private Address address;
}
