package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class AircraftType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;

    @Column(name = "type_name", length = 50)
    @NonNull
    private String typeName;

    @Column(name = "seats")
    @NonNull
    private int seats;
}
