package at.kaindorf.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements Serializable {
    @Id
    private Long address_id;

    @Column(length = 100)
    private String street_name;

    @Column(nullable = false)
    private int street_number;

    @Column(length = 50)
    private String postal_code;

    @Column(length = 100)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();
}
