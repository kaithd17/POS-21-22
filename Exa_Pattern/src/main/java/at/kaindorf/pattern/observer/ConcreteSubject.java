package at.kaindorf.pattern.observer;

public class ConcreteSubject extends Subject{

    private String data;

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}
