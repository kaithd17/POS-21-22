package at.kaindorf.visitor;

import lombok.Data;

import java.nio.file.Path;

@Data
public class DirectorySizeVisitor implements IDirectoryVisitor{
    private int xmlFiles = 0;
    private int dirs = 0;

    @Override
    public void enterDirectory(Path dir) {
        dirs++;
    }

    @Override
    public void leaveDirectory(Path dir) {

    }

    @Override
    public void visitFile(Path file) {
        xmlFiles++;
    }
}
