package comp5911m.sc22ao.cw2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryFilesReader {
    private final String directory;

    public DirectoryFilesReader(String directory) {
        this.directory = directory;
    }

    public List<String> getAllFilesOfType(String fileType) {
        List<String> allFiles = new ArrayList<>();

        try (Stream<Path> allPaths = Files.walk(Paths.get(directory))) {
            if (fileType != null && !fileType.isEmpty()) {
                allFiles = allPaths
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .filter(file -> fileType.equals(file.substring(file.lastIndexOf(".") + 1)))
                        .collect(Collectors.toList());
            } else {
                allFiles = allPaths
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        } catch (IOException ex) {
            System.out.println("Error reading all files: " + ex.getMessage());
        }

        return allFiles;
    }
}
