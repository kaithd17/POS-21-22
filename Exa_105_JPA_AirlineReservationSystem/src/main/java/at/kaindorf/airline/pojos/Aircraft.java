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
public class Aircraft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_id")
    private Long aircraftId;

    @ManyToOne
    @ToString.Exclude
    @NonNull
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "aircraft_type_id")
    @ToString.Exclude
    @NonNull
    private AircraftType aircraftType;

    @ManyToMany
    @JoinTable(
            name="aircraft_airport",
            joinColumns ={@JoinColumn(name="aircraft_id")},
            inverseJoinColumns ={@JoinColumn(name="airport_id")}
    )
    @ToString.Exclude
    private List<Airport> airports = new ArrayList<>();

    @OneToMany(mappedBy = "aircraft", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> flightList = new ArrayList<>();
}
