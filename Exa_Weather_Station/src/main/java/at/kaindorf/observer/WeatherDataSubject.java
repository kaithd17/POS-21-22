package at.kaindorf.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class WeatherDataSubject {
    protected List<WeatherDataObserver> observers = new ArrayList<>();

    public void register(WeatherDataObserver weatherDataObserver) {
        if (!observers.contains(weatherDataObserver)) {
            observers.add(weatherDataObserver);
        }
    }

    public void unregister(WeatherDataObserver weatherDataObserver) {
        observers.remove(weatherDataObserver);
    }

    public abstract void notifyObservers();
}
