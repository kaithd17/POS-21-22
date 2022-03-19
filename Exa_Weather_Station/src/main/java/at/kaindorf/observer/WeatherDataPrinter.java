package at.kaindorf.observer;

import at.kaindorf.beans.Weatherdata;

public class WeatherDataPrinter implements WeatherDataObserver {

    @Override
    public void update(Weatherdata weatherData) {
        System.out.println(weatherData);
    }
}
