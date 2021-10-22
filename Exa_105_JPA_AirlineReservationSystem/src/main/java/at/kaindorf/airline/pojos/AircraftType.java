package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AircraftType implements Serializable {
    @Id
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;

    @Column(name = "type_name", length = 50)
    private String typeName;
    private int seats;
}
