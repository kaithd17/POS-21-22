package at.kaindorf.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<IObserver> observers = new ArrayList<>();

    public void register(IObserver observer) {
        observers.add(observer);
    }

    public void unregister(IObserver observer) {
        observers.remove(observer);
    }

    public abstract void notifyObservers();
}
