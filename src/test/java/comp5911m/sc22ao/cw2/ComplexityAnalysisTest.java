package comp5911m.sc22ao.cw2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


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
        String[] args = new String[] { System.getProperty("user.dir"), "java" };
        DirectoryFilesReader directoryFilesReader = new DirectoryFilesReader(args);
        assertDoesNotThrow(() -> directoryFilesReader.getAllFilesOfType("java"));
    }

    @Test
    void readingPythonFiles() {
        String[] args = new String[] { System.getProperty("user.dir"), "python" };
        DirectoryFilesReader directoryFilesReader = new DirectoryFilesReader(args);
        assertDoesNotThrow(() -> directoryFilesReader.getAllFilesOfType("python"));
    }
}
