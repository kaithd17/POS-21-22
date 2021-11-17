package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Aircraft.getAllAircraftOfAirport", query = "SELECT a FROM Aircraft a JOIN a.airports ar WHERE ar.name = (:airport)"),
        @NamedQuery(name = "Aircraft.getAllAircraftOfAirline", query = "SELECT a FROM Aircraft a WHERE a.airline.airlineName = (:airlineName)")
})
public class Aircraft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_id")
    private Long aircraftId;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @NonNull
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_type_id")
    @NonNull
    private AircraftType aircraftType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="aircraft_airport",
            joinColumns ={@JoinColumn(name="aircraft_id")},
            inverseJoinColumns ={@JoinColumn(name="airport_id")}
    )
    @ToString.Exclude
    private List<Airport> airports = new ArrayList<>();

    @OneToMany(mappedBy = "aircraft", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Flight> flightList = new ArrayList<>();
}
