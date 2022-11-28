package comp5911m.sc22ao.cw2;

import comp5911m.sc22ao.cw2.complexity_analysis.ComplexityAnalyser;
import comp5911m.sc22ao.cw2.complexity_analysis.JavaComplexityAnalyser;
import comp5911m.sc22ao.cw2.complexity_analysis.PythonComplexityAnalyser;

public class MetricAnalyserFactory {
    public ComplexityAnalyser makeComplexityAnalyser(String languageType) {
        if (languageType.equalsIgnoreCase("java")) {
            return new JavaComplexityAnalyser();
        } else if (languageType.equalsIgnoreCase("py")) {
            return new PythonComplexityAnalyser();
        }
        throw new IllegalArgumentException("The language of type " + languageType + " is currently not supported");
    }
}
