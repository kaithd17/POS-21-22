package at.kaindorf.pattern.observer;

public class ConcreteObserver implements Observer{

    private String data;
    private Subject subject;

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        subject.register(this);
    }

    @Override
    public void update(String data) {
        this.data = data;
        //ToDo: do whatever you want with the data
    }
}
