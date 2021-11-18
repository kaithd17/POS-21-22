package at.kaindorf.airline.database;

import at.kaindorf.airline.pojos.*;

import javax.persistence.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class DBAccess {
    private static final Path pathAircraftType = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "aircrafttypes.csv");
    private static final Path pathAirlines = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airlines.csv");
    private static final Path pathAirports = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airports.csv");
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void createData() {
        Random random = new Random();

        Set<AircraftType> aircraftTypeSet = DataImport.getAircraftTypes(pathAircraftType);
        List<Airline> airlineList = DataImport.getAirlines(pathAirlines);
        List<Airport> airportList = DataImport.getAirports(pathAirports);

        List<Aircraft> aircraftList = DataImport.createAircraftList(aircraftTypeSet, airlineList, random);

        List<Flight> flights = DataImport.createFlights(aircraftList, airlineList, airportList, random);
        System.out.println(flights.size());
        flights.forEach(flight -> {
            em.persist(flight);
        });
    }

    public static void queries() {
        TypedQuery<Flight> typedQuery = em.createNamedQuery("Flight.getAllArrivalFlightsOfAirport", Flight.class);
        typedQuery.setParameter("id", 3299);
        List<Flight> flights = typedQuery.getResultList();
        System.out.println("1) AllArrivalFlightsOfAirport: ");
        flights.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Flight> typedQuery1 = em.createNamedQuery("Flight.getAllArrivalFlightsOfCity", Flight.class);
        typedQuery1.setParameter("city", "Buffalo");
        List<Flight> flights1 = typedQuery1.getResultList();
        System.out.println("2) AllArrivalFlightsOfCity: ");
        flights1.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Flight> typedQuery2 = em.createNamedQuery("Flight.getAllDepartureFlightsOfAirport", Flight.class);
        typedQuery2.setParameter("name", "Killian Airfield");
        List<Flight> flights2 = typedQuery2.getResultList();
        System.out.println("3) AllDepartureFlightsOfAirport: ");
        flights2.stream().forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Flight> typedQuery3 = em.createNamedQuery("Flight.getAllDepartureFlightsOfCity", Flight.class);
        typedQuery3.setParameter("city", "Cambridge");
        List<Flight> flights3 = typedQuery3.getResultList();
        System.out.println("4) AllDepartureFlightsOfCity: ");
        flights3.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Flight> typedQuery4 = em.createNamedQuery("Flight.getAllFlightsOfAircraft", Flight.class);
        typedQuery4.setParameter("type", "JIM T LANE");
        List<Flight> flights4 = typedQuery4.getResultList();
        System.out.println("5) AllFlightsOfAircraft: ");
        flights4.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Flight> typedQuery5 = em.createNamedQuery("Flight.getAllFlightsOfAirline", Flight.class);
        typedQuery5.setParameter("airlineName", "Air Atonabee");
        List<Flight> flights5 = typedQuery5.getResultList();
        System.out.println("6) AllFlightsOfAirline: ");
        flights5.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Airline> typedQuery6 = em.createNamedQuery("Airline.getAirlineOfAircraftType", Airline.class);
        typedQuery6.setParameter("name", "BOEING-VERTOL");
        List<Airline> airlines = typedQuery6.getResultList();
        System.out.println("7) AirlineOfAircraftType: ");
        airlines.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Airport> typedQuery7 = em.createNamedQuery("Airport.getAllAirportsOfAirline", Airport.class);
        typedQuery7.setParameter("airlineName", "Araiavia");
        List<Airport> airports = typedQuery7.getResultList();
        System.out.println("8) AllAirportsOfAirline: ");
        airports.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Aircraft> typedQuery8 = em.createNamedQuery("Aircraft.getAllAircraftOfAirline", Aircraft.class);
        typedQuery8.setParameter("airlineName", "Araiavia");
        List<Aircraft> aircrafts = typedQuery8.getResultList();
        System.out.println("9) AllAircraftOfAirline: ");
        aircrafts.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Aircraft> typedQuery9 = em.createNamedQuery("Aircraft.getAllAircraftOfAirport", Aircraft.class);
        typedQuery9.setParameter("airport", "Collier Farms Airport");
        List<Aircraft> aircrafts1 = typedQuery9.getResultList();
        System.out.println("10) AllAircraftOfAirport: ");
        aircrafts1.forEach(System.out::println);
        System.out.println("\n");
    }


    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("PU_Airline_Database");
        em = emf.createEntityManager();

        em.getTransaction().begin();
        createData();
        em.getTransaction().commit();
        //If you want to test queries, do not change the current database!
        queries();

    }
}
