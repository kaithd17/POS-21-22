package at.kaindorf.observer;

import at.kaindorf.beans.Weatherdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class WeatherDataLogger implements WeatherDataObserver {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ISO_DATE_TIME;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private static File weatherDataFile = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherData.csv").toFile();

    public WeatherDataLogger() {
        try {
            fileWriter = new FileWriter(weatherDataFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("timestamp;temperature;pressure;humidity;windspeed;windDirection\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fileWriter.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Weatherdata weatherData) {
        try {
            bufferedWriter.append(DTF.format(weatherData.getDateTime()) + ";" + weatherData.getTemperature() + ";" + weatherData.getPressure() + ";" + weatherData.getHumidity() + ";" + weatherData.getWindSpeed() + ";" + weatherData.getWindDirection()+ "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
