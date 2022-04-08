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
        MyPath.xmlFileList.add(file);
    }
}
