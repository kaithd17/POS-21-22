package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long flightId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(cascade = CascadeType.ALL)
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport")
    private Airport departureAirport;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport")
    private Airport arrivalAirport;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
}
