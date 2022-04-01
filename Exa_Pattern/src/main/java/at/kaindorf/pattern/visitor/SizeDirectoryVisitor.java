package at.kaindorf.pattern.visitor;

import lombok.Data;

import java.io.File;

@Data
public class SizeDirectoryVisitor implements DirectoryVisitor{
    private int dirs = 0;
    private int files = 0;
    private long size = 0;


    @Override
    public void enterDirectory(File dir) {
        dirs++;
    }

    @Override
    public void leaveDirectory(File dir) {
    }

    @Override
    public void visitFile(File file) {
        files++;
        size += file.length();
    }
}
