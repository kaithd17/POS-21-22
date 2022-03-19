package at.kaindorf.conprod;

import at.kaindorf.beans.Weatherdata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class WeatherDataProducer implements Runnable {
    public static final Path PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherdata.json");
    private Queue<Weatherdata> weatherDataQueue;

    public WeatherDataProducer(Queue<Weatherdata> weatherDataQueue) {
        this.weatherDataQueue = weatherDataQueue;
    }

    @Override
    public void run() {
        List<Weatherdata> weatherDataList = new ArrayList<>();
        try {
            weatherDataList = new ObjectMapper().readValue(PATH.toFile(), new TypeReference<List<Weatherdata>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        long period;
        for (int i = 0; i < weatherDataList.size(); i++) {
            synchronized (weatherDataQueue) {
                if (i != weatherDataList.size()-1) {
                    period = ChronoUnit.SECONDS.between(weatherDataList.get(i).getDateTime(), weatherDataList.get(i + 1).getDateTime()) * 1000;
                } else {
                    period = 0;
                }
                weatherDataQueue.add(weatherDataList.get(i));
            }
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
