package at.kaindorf.airline.pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Flight.getAllFlightsOfAircraft", query = "SELECT f FROM Flight f WHERE f.aircraft.aircraftType.typeName = (:type)"),
        @NamedQuery(name = "Flight.getAllArrivalFlightsOfAirport", query = "SELECT f FROM Flight f  WHERE f.arrivalAirport.airportId = (:id)"),
        @NamedQuery(name = "Flight.getAllDepartureFlightsOfAirport", query = "SELECT f FROM Flight f  WHERE f.departureAirport.name = (:name)"),
        @NamedQuery(name = "Flight.getAllFlightsOfAirline", query = "SELECT f FROM Flight f  WHERE f.airline.airlineName = (:airlineName)"),
        @NamedQuery(name = "Flight.getAllArrivalFlightsOfCity", query = "SELECT f FROM Flight f WHERE f.arrivalAirport.city = (:city)"),
        @NamedQuery(name = "Flight.getAllDepartureFlightsOfCity", query = "SELECT f FROM Flight f WHERE f.departureAirport.city = (:city)"),
})
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "flight_id")
    private Long flightId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    @NonNull
    private Aircraft aircraft;

    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport")
    @NonNull
    private Airport departureAirport;

    @Column(name = "departure_time")
    @NonNull
    private LocalTime departureTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport")
    @NonNull
    private Airport arrivalAirport;

    @Column(name = "arrival_time")
    @NonNull
    private LocalTime arrivalTime;
}
