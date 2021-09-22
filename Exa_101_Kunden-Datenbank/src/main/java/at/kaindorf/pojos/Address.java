package at.kaindorf.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements Serializable {
    @Id
    private Long address_id;

    @Column(length = 100)
    private String street_name;

    @Column(nullable = false)
    @NonNull
    private int street_number;

    private String postal_code;
    private String city;
}
