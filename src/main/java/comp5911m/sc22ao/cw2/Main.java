package comp5911m.sc22ao.cw2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MetricsReporterApplication metricsReporterApplication = new MetricsReporterApplication(
                new MetricAnalyserFactory(),
                args);

        metricsReporterApplication.analyseMaxAndAvgComplexityOfAllClasses();
    }
}