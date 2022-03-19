package at.kaindorf.main;

import at.kaindorf.beans.Weatherdata;
import at.kaindorf.conprod.WeatherDataConsumer;
import at.kaindorf.conprod.WeatherDataProducer;
import at.kaindorf.observer.WeatherDataGUI;
import at.kaindorf.observer.WeatherDataLogger;
import at.kaindorf.observer.WeatherDataPrinter;

import java.util.LinkedList;
import java.util.Queue;

public class WeatherDataLauncher {
    public static void main(String[] args) {
        Queue<Weatherdata> queue = new LinkedList<>();
        WeatherDataProducer weatherDataProducer = new WeatherDataProducer(queue);
        WeatherDataConsumer weatherDataConsumer = new WeatherDataConsumer(queue);

        WeatherDataLogger weatherDataLogger = new WeatherDataLogger();
        WeatherDataPrinter weatherDataPrinter = new WeatherDataPrinter();
        WeatherDataGUI weatherDataGUI = new WeatherDataGUI();

        //register concreteObservers
        weatherDataConsumer.register(weatherDataLogger);
        weatherDataConsumer.register(weatherDataPrinter);
        weatherDataConsumer.register(weatherDataGUI);

        //starting threads
        Thread threadC = new Thread(weatherDataConsumer);
        threadC.start();
        Thread threadP = new Thread(weatherDataProducer);
        threadP.start();

    }
}
