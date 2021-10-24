package at.kaindorf.airline.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    private Long flightId;

    @ManyToOne
    @Column(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    @Column(name = "airline_id")
    private Airline airlineId;

    @ManyToOne
    @Column(name = "airline_name")
    private Airline airlineName;

    @ManyToOne
    @Column(name = "departure_airport")
    private Airport departureAirport;

    @Column(name = "departure_time")
    private Time departureTime;

    @ManyToOne
    @Column(name = "arrival_airport")
    private Airport arrivalAirport;

    @Column(name = "arrival_time")
    private Time arrivalTime;
}
