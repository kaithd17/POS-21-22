package at.kaindorf.airline.database;

import at.kaindorf.airline.pojos.*;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class DataImport {

    public static Set<String> readFile(Path path, int skipLines) {
        Set<String> dataSet = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            dataSet = br.lines().skip(skipLines).map(String::toString).collect(Collectors.toSet());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public static Set<AircraftType> getAircraftTypes(Path path) {
        Set<String> dataSet = readFile(path, 1);
        Set<AircraftType> aircraftTypeList = new HashSet<>();

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].contains(" ")) {
                    while (true) {
                        if (stringArray[i].charAt(stringArray[i].length() - 1) == ' ') {
                            stringArray[i] = stringArray[i].substring(0, (stringArray[i].length() - 1));
                        } else {
                            break;
                        }
                    }
                }
            }
            aircraftTypeList.add(new AircraftType(stringArray[1], Integer.parseInt(stringArray[8])));
        });

        return aircraftTypeList;
    }

    public static List<Airline> getAirlines(Path path) {
        List<Airline> airlineList = new ArrayList<>();
        Set<String> dataSet = readFile(path, 0);

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airlineList.add(new Airline(Long.parseLong(stringArray[0]), stringArray[1]));
        });

        return airlineList;
    }

    public static List<Airport> getAirports(Path path) {
        Set<String> dataSet = readFile(path, 1);
        List<Airport> airportList = new ArrayList<>();

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airportList.add(new Airport(stringArray[8], stringArray[10], stringArray[3]));
        });

        airportList.forEach(airport -> {
            if (airport.getCity().equals(""))
                airport.setCity("unknown");
        });

        return airportList;
    }

    public static List<Aircraft> createAircraftList(Set<AircraftType> aircraftTypeSet, List<Airline> airlineList, Random random) {
        List<Aircraft> aircraftList = new ArrayList<>();
        //convert set to list
        List<AircraftType> aircraftTypeList = aircraftTypeSet.stream().collect(Collectors.toList());
        airlineList.forEach(airline -> {
            //To limit the objects
            if (airline.getAirlineId() == (airlineList.size() / 2))
                return;
            aircraftList.add(new Aircraft(airline, aircraftTypeList.get(random.nextInt(aircraftTypeList.size()))));
        });
        return aircraftList;
    }

    public static List<Flight> createFlights(List<Aircraft> aircraftList, List<Airline> airlineList, List<Airport> airportList, Random random) {
        List<Flight> flights = new ArrayList<>();

        //Create flights
        int counter = 0;
        for (Airline airline : airlineList) {
            if (counter == 2700)
                break;
            LocalTime departureTime = LocalTime.now().minus(random.nextInt(12), ChronoUnit.HOURS).minus(random.nextInt(59), ChronoUnit.MINUTES);
            LocalTime arrivalTime = LocalTime.now().plus(random.nextInt(12), ChronoUnit.HOURS).plus(random.nextInt(59), ChronoUnit.MINUTES);
            flights.add(new Flight(
                    aircraftList.get(random.nextInt(aircraftList.size())),
                    airline,
                    airportList.get(random.nextInt(airportList.size())),
                    departureTime,
                    airportList.get(random.nextInt(airportList.size())),
                    arrivalTime));
            counter++;
        }

        bidirectional(flights, random, airportList);
        return flights;
    }

    public static void bidirectional(List<Flight> flights, Random random, List<Airport> airports) {
        flights.forEach(flight -> {
            //Added the flight object to the list of all entities
            flight.getAircraft().getFlightList().add(flight);
            flight.getAirline().getFlights().add(flight);
            flight.getDepartureAirport().getDepartureFlights().add(flight);
            flight.getArrivalAirport().getArrivalFlights().add(flight);
            flight.getAircraft().getAirline().getAircraftList().add(flight.getAircraft());

            //Bidirectional aircraft and airport
            flight.getAircraft().getAirports().add(airports.get(random.nextInt(airports.size())));
            //Add aircraft to the newest airport
            flight.getAircraft().getAirports().get(flight.getAircraft().getAirports().size() - 1).getAircraftList().add(flight.getAircraft());
        });
    }
}
