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
@NamedQueries({
        @NamedQuery(name = "Airline.getAirlineOfAircraftType", query = "SELECT a FROM Airline a JOIN a.aircraftList al WHERE al.aircraftType.typeName = (:name)")
})
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    @NonNull
    private Long airlineId;

    @Id
    @Column(name = "airline_name", length = 255)
    @NonNull
    private String airlineName;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "airline")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aircraft> aircraftList = new ArrayList<>();


}
