package comp5911m.sc22ao.cw2;

import comp5911m.sc22ao.cw2.complexity_analysis.ComplexityAnalyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetricsReporterApplication {
    private MetricAnalyserFactory metricAnalyserFactory;
    private DirectoryFilesReader directoryFilesReader;
    private List<String> fileExtensionsToBeAnalysed;

    public MetricsReporterApplication(MetricAnalyserFactory complexityAnalyserFactory,
                                      String[] commandLineArgs) {
        this.metricAnalyserFactory = complexityAnalyserFactory;
        this.directoryFilesReader = new DirectoryFilesReader(findDirectoryToPerformAnalysis(commandLineArgs));
        this.fileExtensionsToBeAnalysed = findFileExtensionsToBeAnalyzed(commandLineArgs);
    }

    public void analyseMaxAndAvgComplexityOfAllClasses() throws IOException {
        for (String fileExtension : fileExtensionsToBeAnalysed) {
            try {
                ComplexityAnalyser complexityAnalyser = metricAnalyserFactory.makeComplexityAnalyser(fileExtension);
                List<String> files = directoryFilesReader.getAllFilesOfType(fileExtension);

                if (!files.isEmpty()) {
                    complexityAnalyser.evaluateComplexity(files);

                    System.out.println("Complexity Metrics for file extension: " + fileExtension);
                    complexityAnalyser.printMaximumComplexityMetrics();
                    complexityAnalyser.printAverageComplexityMetrics();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
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
}
