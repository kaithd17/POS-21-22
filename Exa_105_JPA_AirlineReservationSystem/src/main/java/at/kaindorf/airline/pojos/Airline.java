package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airline implements Serializable {
    @Id
    @Column(name = "airline_id")
    private Long airlineId;

    @Id
    @Column(name = "airline_name", length = 40)
    private String airlineName;
}
