package at.kaindorf.conprod;

import at.kaindorf.beans.Weatherdata;
import at.kaindorf.observer.WeatherDataSubject;

import java.util.Queue;

public class WeatherDataConsumer extends WeatherDataSubject implements Runnable {
    private Queue<Weatherdata> weatherDataQueue;
    private Weatherdata weatherdata;

    public WeatherDataConsumer(Queue<Weatherdata> weatherDataQueue) {
        this.weatherDataQueue = weatherDataQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (weatherDataQueue) {
                weatherdata = weatherDataQueue.poll();

                if (weatherdata != null) {
                    notifyObservers();
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(weatherdata));
    }
}
