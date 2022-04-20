package at.kaindorf.visitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchXMLFiles {
    private int directories;
    private int files;
    private Path workingDirectory;

    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    private void traverse(File startDir, IDirectoryVisitor visitor) {
        if (!startDir.isDirectory()) {
            throw new IllegalArgumentException(startDir.getName() + " is not a directory");
        }
        File[] dirContent = startDir.listFiles();
        visitor.enterDirectory(startDir.toPath());
        for (File file : dirContent) {
            if (file.isDirectory()) {
                traverse(file, visitor);
            } else {
                visitor.visitFile(file.toPath());
            }
        }
        visitor.leaveDirectory(startDir.toPath());
    }

    public void searchXmlFiles() {
        SearchXMLFiles searchXMLFiles = new SearchXMLFiles();
        DirectorySizeVisitor directorySizeVisitor = new DirectorySizeVisitor();
        searchXMLFiles.traverse(workingDirectory.toFile(), directorySizeVisitor);

        directories = directorySizeVisitor.getDirs();
        files = directorySizeVisitor.getXmlFiles();
    }

    public void getXmlFiles() {
        SearchXMLFiles searchXMLFiles = new SearchXMLFiles();
        searchXMLFiles.traverse(workingDirectory.toFile(), new DirectoryXMLFileVisitor());
    }

    /*public static void main(String[] args) {
        SearchXMLFiles searchXMLFiles = new SearchXMLFiles();
        searchXMLFiles.searchXmlFiles();
        searchXMLFiles.getXmlFiles();
    }*/
}
