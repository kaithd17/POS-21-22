package at.kaindorf.pattern.observer.consumerproducer;

import java.util.List;

public class Consumer implements Runnable{
    private List<String> stringList;

    public Consumer(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public void run() {
        synchronized (stringList) {

        }
    }
}
