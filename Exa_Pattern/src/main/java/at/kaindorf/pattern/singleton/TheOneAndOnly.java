package at.kaindorf.pattern.singleton;

public class TheOneAndOnly {
    private static TheOneAndOnly theInstance;

    public static TheOneAndOnly getInstance() {
        if (theInstance == null) {
            theInstance = new TheOneAndOnly();
        }
        return theInstance;
    }

    private TheOneAndOnly() {
    }
}
