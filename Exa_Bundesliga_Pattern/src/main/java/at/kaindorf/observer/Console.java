package at.kaindorf.observer;

public class Console implements IObserver {

    @Override
    public void update(String data) {
        System.out.println(data);
    }
}
