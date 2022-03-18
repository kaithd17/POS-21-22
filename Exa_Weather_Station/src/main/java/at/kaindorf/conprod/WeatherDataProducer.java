package at.kaindorf.conprod;

import at.kaindorf.beans.Weatherdata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class WeatherDataProducer implements Runnable {
    public static final Path PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weather.json");
    private List<Weatherdata> weatherDataList = new ArrayList<>();
    private Queue<Weatherdata> weatherdataQueue;

    public WeatherDataProducer(Queue<Weatherdata> weatherdataQueue) {
        this.weatherdataQueue = weatherdataQueue;
        getData();
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            synchronized (weatherdataQueue){

            }
        }
    }

    public void getData(){
        try {
            weatherDataList = new ObjectMapper().readValue(PATH.toFile(), new TypeReference<List<Weatherdata>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
