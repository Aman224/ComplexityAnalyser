package comp5911m.sc22ao.cw2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryFilesReader {
    private final String directory;
    private final List<String> fileExtensions;

    public DirectoryFilesReader(String[] args) {
        this.directory = findDirectoryToPerformAnalysis(args);
        this.fileExtensions = findFileExtensionsToBeAnalyzed(args);
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

    private String findDirectoryToPerformAnalysis(String[] args) {
        String directoryToPerformAnalysis;
        if (args.length == 0) {
            directoryToPerformAnalysis = System.getProperty("user.dir");
        } else {
            directoryToPerformAnalysis = args[0];
        }
        return directoryToPerformAnalysis;
    }

    private List<String> findFileExtensionsToBeAnalyzed(String[] args) {
        if (args.length < 2) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        }
    }

    public List<String> getFileTypesToBeAnalysed() {
        return fileExtensions;
    }
}
