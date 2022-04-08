package at.kaindorf.visitor;

import java.nio.file.Path;

public interface IDirectoryVisitor {
     void enterDirectory(Path dir);
     void leaveDirectory(Path dir);
     void visitFile(Path file);
}
