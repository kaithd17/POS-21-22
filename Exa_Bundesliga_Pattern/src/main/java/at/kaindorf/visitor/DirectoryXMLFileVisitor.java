package at.kaindorf.visitor;

import java.nio.file.Path;

public class DirectoryXMLFileVisitor implements IDirectoryVisitor{
    @Override
    public void enterDirectory(Path dir) {

    }

    @Override
    public void leaveDirectory(Path dir) {

    }

    @Override
    public void visitFile(Path file) {
        if (file.toString().contains(".xml") && !(file.toString().contains("bundesliga2122.xml"))) {
            MyPath.getInstance().xmlFileList.add(file);
        }
    }
}
