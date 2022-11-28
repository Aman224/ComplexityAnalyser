package comp5911m.sc22ao.cw2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class ComplexityAnalysisTest {
    MetricsReporterApplication metricsReporterApplication;

    @Test
    void creation() {
        MetricAnalyserFactory metricAnalyserFactory = new MetricAnalyserFactory();
        String[] testArgs = new String[] {System.getProperty("user.dir"), "java"};
        metricsReporterApplication = new MetricsReporterApplication(metricAnalyserFactory, testArgs);
        assertThat(metricsReporterApplication, is(notNullValue()));
    }

    @Test
    void readingJavaFiles() {
        DirectoryFilesReader directoryFilesReader = new DirectoryFilesReader(System.getProperty("user.dir"));
        directoryFilesReader.getAllFilesOfType("java");
    }

    @Test
    void readingPythonFiles() {
        DirectoryFilesReader directoryFilesReader = new DirectoryFilesReader(System.getProperty("user.dir"));
        directoryFilesReader.getAllFilesOfType("java");
    }
}
