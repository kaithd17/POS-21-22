package at.kaindorf.airline.console;

import at.kaindorf.airline.io.IOHandler;
import at.kaindorf.airline.pojos.AircraftType;
import at.kaindorf.airline.pojos.Airline;
import at.kaindorf.airline.pojos.Airport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Main {
    private static final Path pathAircraftType = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "aircrafttypes.csv");
    private static final Path pathAirlines = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airlines.csv");
    private static final Path pathAirports = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airports.csv");

    public static void createData() {
        Set<AircraftType> aircraftTypeSet = IOHandler.getAircraftTypes(pathAircraftType);
        Set<Airline> airlineSet = IOHandler.getAirlines(pathAirlines);
        Set<Airport> airportSet = IOHandler.getAirports(pathAirports);
        airportSet.forEach(System.out::println);
    }

    public static void main(String[] args) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Airline_Database");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();*/
        createData();
    }
}
