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
@IdClass(AirlinePK.class)
/*@NamedQueries({
        @NamedQuery(name = "Airline.getAllAircraftOfAirline", query = "SELECT a FROM Airline a JOIN a.aircraftList al WHERE al.airline.airlineName = (:airlineName)")
})*/
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    @NonNull
    private Long airlineId;

    @Id
    @Column(name = "airline_name", length = 40)
    @NonNull
    private String airlineName;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    @ToString.Exclude
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "airline")
    @ToString.Exclude
    private List<Aircraft> aircraftList = new ArrayList<>();
}
