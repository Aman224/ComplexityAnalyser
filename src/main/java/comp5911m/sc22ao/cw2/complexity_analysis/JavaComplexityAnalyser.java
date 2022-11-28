package comp5911m.sc22ao.cw2.complexity_analysis;

import comp5911m.sc22ao.cw2.parse_listener.ComplexityListener;
import comp5911m.sc22ao.cw2.parse_listener.JavaComplexityMetricsListener;
import comp5911m.sc22ao.cw2.parser.java.JavaLexer;
import comp5911m.sc22ao.cw2.parser.java.JavaParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.List;

public class JavaComplexityAnalyser extends ComplexityAnalyser {
    public JavaComplexityAnalyser() {
        this.complexityListener = new JavaComplexityMetricsListener();
    }

    @Override
    public void evaluateComplexity(List<String> files) throws IOException {
        for (String file : files) {
            JavaLexer lexer = new JavaLexer(CharStreams.fromFileName(file));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);
            ParseTree parseTree = parser.compilationUnit();
            ParseTreeWalker walker = new ParseTreeWalker();

            ComplexityListener complexityListener = new JavaComplexityMetricsListener();
            walker.walk(complexityListener, parseTree);

            updateMaximumAndAverageComplexityMap(file);
        }
    }
}
