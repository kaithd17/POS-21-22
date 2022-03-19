package at.kaindorf.observer;

import at.kaindorf.beans.Weatherdata;

public interface WeatherDataObserver {
    void update(Weatherdata weatherData);
}
