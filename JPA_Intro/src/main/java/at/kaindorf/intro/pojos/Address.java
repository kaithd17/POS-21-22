package at.kaindorf.intro.pojos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Address implements Serializable {
    @Id
    @Column(name = "addressId", nullable = false)
    @GeneratedValue
    private Long addressId;
    @Column(length = 100, nullable = false)
    @NonNull
    private String city;
    @Column(length = 100, nullable = false)
    @NonNull
    private String street;
    @Column(length = 100, nullable = false)
    @NonNull
    private String number;
}
