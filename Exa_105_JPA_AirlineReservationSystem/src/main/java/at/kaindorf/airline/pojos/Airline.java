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
@IdClass(AirlinePK.class)
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    private Long airlineId;

    @Id
    @Column(name = "airline_name", length = 40)
    private String airlineName;

    @OneToMany(mappedBy = "airline")
    @ToString.Exclude
    private List<Flight> flights;

    @OneToMany(mappedBy = "airline", orphanRemoval = true)
    @ToString.Exclude
    private List<Aircraft> aircraftList;
}
