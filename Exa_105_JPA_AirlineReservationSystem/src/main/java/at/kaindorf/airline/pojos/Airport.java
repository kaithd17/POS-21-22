package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Airport.getAllAirportsOfAirline", query = "SELECT a FROM Airport a JOIN a.aircraftList al WHERE al.airline.airlineName = (:airlineName)")
})
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "airport_id")
    private Long airportId;

    @Column(length = 60)
    @NonNull
    private String country;

    @Column(length = 50)
    @NonNull
    private String city;

    @Column(length = 100)
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "airports")
    @ToString.Exclude
    private List<Aircraft> aircraftList = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> arrivalFlights = new ArrayList<>();

    @OneToMany(mappedBy = "departureAirport", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> departureFlights = new ArrayList<>();
}
