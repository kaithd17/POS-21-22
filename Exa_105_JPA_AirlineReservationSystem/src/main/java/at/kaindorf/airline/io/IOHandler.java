package at.kaindorf.airline.io;

import at.kaindorf.airline.pojos.AircraftType;
import at.kaindorf.airline.pojos.Airline;
import at.kaindorf.airline.pojos.Airport;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IOHandler {

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
}
