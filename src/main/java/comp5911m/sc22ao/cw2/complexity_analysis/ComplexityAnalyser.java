package comp5911m.sc22ao.cw2.complexity_analysis;

import comp5911m.sc22ao.cw2.parse_listener.ComplexityListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ComplexityAnalyser {
    protected Map<String, Integer> classToMaximumComplexityMap;
    protected Map<String, Double> classToAverageComplexityMap;
    protected ComplexityListener complexityListener;

    public ComplexityAnalyser() {
        this.classToMaximumComplexityMap = new HashMap<>();
        this.classToAverageComplexityMap = new HashMap<>();
    }

    public abstract void evaluateComplexity(List<String> files) throws IOException;

    public void printMaximumComplexityMetrics() {
        System.out.println("Maximum McConnell's Complexity Metrics");
        System.out.format("%-150s%-30s\n", "Class/File Absolute Path", "Maximum Complexity");
        for (var entry : classToMaximumComplexityMap.entrySet()) {
            System.out.format("%-150s%-30d\n", entry.getKey(),entry.getValue());
        }
        System.out.println();
    }

    public void printAverageComplexityMetrics() {
        System.out.println("Average McConnell's Complexity Metrics");
        System.out.format("%-150s%-30s\n", "Class/File Absolute Path", "Average Complexity");
        for (var entry : classToAverageComplexityMap.entrySet()) {
            System.out.format("%-150s%-30f\n", entry.getKey(),entry.getValue());
        }
        System.out.println();
    }

    protected void updateMaximumAndAverageComplexityMap(String fileName) {
        Integer maximum = 0;
        Integer total = 0;
        for (var entry : complexityListener.getMethodToComplexityMap().entrySet()) {
            if (entry.getValue() > maximum) {
                maximum = entry.getValue();
            }
            total += entry.getValue();
        }
        Double average = (double) total / complexityListener.getMethodToComplexityMap().size();
        classToMaximumComplexityMap.put(fileName, maximum);
        classToAverageComplexityMap.put(fileName, average);

        complexityListener.clearMethodToComplexityMap();
    }
}
