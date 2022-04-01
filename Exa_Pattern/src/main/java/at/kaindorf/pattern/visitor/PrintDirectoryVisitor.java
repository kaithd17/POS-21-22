package at.kaindorf.pattern.visitor;

import java.io.File;

public class PrintDirectoryVisitor implements DirectoryVisitor{
    private String indent = "";

    @Override
    public void enterDirectory(File dir) {
        System.out.println(indent + "[" + dir.getName() + "]");
        indent += "  ";
    }

    @Override
    public void leaveDirectory(File dir) {
        indent = indent.substring(2);
    }

    @Override
    public void visitFile(File file) {
        System.out.println(indent + file.getName());
    }
}
