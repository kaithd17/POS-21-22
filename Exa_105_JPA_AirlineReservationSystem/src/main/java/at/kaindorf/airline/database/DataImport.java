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

    public static Set<Airline> getAirlines(Path path) {
        Set<Airline> airlineSet = new HashSet<>();
        Set<String> dataSet = readFile(path, 0);

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airlineSet.add(new Airline(Long.parseLong(stringArray[0]), stringArray[1]));
        });

        return airlineSet;
    }

    public static Set<Airport> getAirports(Path path) {
        Set<String> dataSet = readFile(path, 1);
        Set<Airport> airportSet = new HashSet<>();
        List<Airport> airportList = new ArrayList<>();

        dataSet.forEach(s -> {
            String[] stringArray = s.split(",");
            airportList.add(new Airport(stringArray[8], stringArray[10], stringArray[3]));
        });

        airportList.forEach(airport -> {
            if (airport.getCity().equals(""))
                airport.setCity("unknown");
            airportSet.add(airport);
        });

        return airportSet;
    }

    public static Set<Aircraft> createAircraftSet(Set<AircraftType> aircraftTypeSet, Set<Airline> airlineSet, Random random) {
        Set<Aircraft> aircraftSet = new HashSet<>();
        //convert set to list
        List<AircraftType> aircraftTypeList = aircraftTypeSet.stream().collect(Collectors.toList());
        airlineSet.forEach(airline -> {
            if (airline.getAirlineId() == (airlineSet.size() / 2))
                return;
            aircraftSet.add(new Aircraft(airline, aircraftTypeList.get(random.nextInt(aircraftTypeList.size()))));
        });
        return aircraftSet;
    }

    public static List<Flight> createFlights(Set<Aircraft> aircraftSet, Set<Airline> airlineSet, Set<Airport> airportSet, Random random) {
        List<Flight> flights = new ArrayList<>();
        //Convert sets to lists
        List<Aircraft> aircraftList = aircraftSet.stream().collect(Collectors.toList());
        List<Airport> airportList = airportSet.stream().collect(Collectors.toList());

        //Create flights
        int counter = 0;
        for (Airline airline : airlineSet) {
            if (counter == 2500)
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
