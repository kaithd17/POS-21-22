package at.kaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer implements Serializable {
    @Id
    private Long customer_id;

    @Column(length = 100)
    private String firstname;

    @Column(length = 100)
    private String lastname;

    @Column(length = 1, nullable = false)
    private char gender;

    @Column(nullable = false)
    private boolean active;

    @Column(length = 255)
    private String email;

    @Column(name = "since")
    private LocalDate since;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;
}
