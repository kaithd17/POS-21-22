package at.kaindorf.visitor;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MyPath {
    //Singleton
    private static MyPath theInstance;
    public static MyPath getInstance() {
        if (theInstance == null) {
            theInstance = new MyPath();
        }
        return theInstance;
    }
    private MyPath() {}

    public static List<Path> xmlFileList = new ArrayList<>();
}
