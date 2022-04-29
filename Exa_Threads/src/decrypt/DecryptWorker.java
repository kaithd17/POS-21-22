package decrypt;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class DecryptWorker implements Callable<String> {

    private static int[] numbers;
    private char c1;
    private static final String[] commonWords = {"and", "of", "that", "and", "at", "for", "with"};

    public static void setNumbers(int[] numbers) {
        DecryptWorker.numbers = numbers;
    }

    public DecryptWorker(char c1) {
        this.c1 = c1;
    }

    @Override
    public String call() throws Exception {
        for (char c2 = 'a'; c2 <= 'z' ; c2++) {
            for (char c3 = 'a'; c3 <= 'z' ; c3++) {
                StringBuffer text = new StringBuffer(numbers.length);
                for (int i = 0; i < numbers.length; i++) {
                    int key = i % 3 == 0 ? c1 : i % 3 == 1 ? c2 : c3;
                    text.append((char) (numbers[i] ^ key));
                }
                long amount = Arrays.stream(commonWords)
                        .filter(word -> text.toString().contains(word))
                        .count();
                if (amount >= 4) {
                    return text.toString();
                }
            }
        }
        throw new Exception("not found");
    }
}
