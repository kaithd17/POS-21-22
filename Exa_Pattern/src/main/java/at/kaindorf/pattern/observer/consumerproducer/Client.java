package at.kaindorf.pattern.observer.consumerproducer;

import java.util.LinkedList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        Producer producer = new Producer(strings);
        Consumer consumer = new Consumer(strings);

        Thread threadProd = new Thread(producer);
        Thread threadCons = new Thread(consumer);

        threadProd.start();
        threadCons.start();
    }
}
