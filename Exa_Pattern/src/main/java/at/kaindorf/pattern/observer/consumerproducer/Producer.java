package at.kaindorf.pattern.observer.consumerproducer;

import java.util.List;
import java.util.Random;

public class Producer implements Runnable{

    private List<String> stringList;
    private static final String[] stringArray = {"Anton", "Berta", "Caesar"};

    public Producer(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (!Thread.interrupted()) {
            synchronized (stringList) {
                stringList.add(stringArray[rand.nextInt()]);
            }
        }
    }
}
