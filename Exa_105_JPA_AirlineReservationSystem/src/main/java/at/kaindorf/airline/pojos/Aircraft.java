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
public class Aircraft implements Serializable {
    @Id
    @Column(name = "aircraft_id")
    private Long aircraftId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Airline airline;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_type_id")
    @ToString.Exclude
    private AircraftType aircraftType;

    @ManyToMany(mappedBy = "airportId")
    @ToString.Exclude
    private List<Airport> airports;
}
