package decrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DecryptLauncher {
    public void loadData() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"),"src","res","cypher.txt");

        int[] numbers = Arrays.stream(Files.readString(path).split(",")).mapToInt(Integer::parseInt).toArray();
        DecryptWorker.setNumbers(numbers);
    }

    public void runTasks(){
        ExecutorService pool= Executors.newFixedThreadPool(4);
        List<DecryptWorker> workers = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            workers.add(new DecryptWorker(i));
        }
        try {
            //invokeAll: jeder liefert wert zurück mit futureobjekt
            //invokeAny: einer liefert zurück, dann beendet alle anderen Exception
            String resultText = pool.invokeAny(workers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    public DecryptLauncher(){
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DecryptLauncher l = new DecryptLauncher();
        l.runTasks();
    }
}
