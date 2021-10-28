package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "airport_id")
    private Long airportId;

    @Column(length = 60)
    private String country;

    @Column(length = 50)
    private String city;

    @Column(length = 60)
    private String name;

    @ManyToMany(mappedBy = "airports")
    @ToString.Exclude
    private List<Aircraft> aircraftList;

    @OneToMany
    @ToString.Exclude
    private List<Flight> arrivalFlights;

    @OneToMany
    @ToString.Exclude
    private List<Flight> departureFlights;
}
