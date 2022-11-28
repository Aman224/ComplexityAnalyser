package comp5911m.sc22ao.cw2;

import comp5911m.sc22ao.cw2.complexity_analysis.ComplexityAnalyser;

import java.io.IOException;
import java.util.List;

public class MetricsReporterApplication {
    private MetricAnalyserFactory metricAnalyserFactory;
    private DirectoryFilesReader directoryFilesReader;

    public MetricsReporterApplication(MetricAnalyserFactory complexityAnalyserFactory,
                                      String[] commandLineArgs) {
        this.metricAnalyserFactory = complexityAnalyserFactory;
        this.directoryFilesReader = new DirectoryFilesReader(commandLineArgs);
    }

    public void analyseMaxAndAvgComplexityOfAllClasses() throws IOException {
        for (String fileExtension : directoryFilesReader.getFileTypesToBeAnalysed()) {
            try {
                ComplexityAnalyser complexityAnalyser = metricAnalyserFactory.makeComplexityAnalyser(fileExtension);
                List<String> files = directoryFilesReader.getAllFilesOfType(fileExtension);

                if (!files.isEmpty()) {
                    complexityAnalyser.evaluateComplexity(files);

                    System.out.println("Complexity Metrics for file extension: " + fileExtension);
                    complexityAnalyser.printMaximumComplexityMetrics();
                    complexityAnalyser.printAverageComplexityMetrics();
                } else {
                    System.out.println("No files of type " + fileExtension + " detected in the directory");
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
