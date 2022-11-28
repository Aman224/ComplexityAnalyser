package comp5911m.sc22ao.cw2.complexity_analysis;

import comp5911m.sc22ao.cw2.parse_listener.PythonComplexityMetricsListener;
import comp5911m.sc22ao.cw2.parser.python.PythonLexer;
import comp5911m.sc22ao.cw2.parser.python.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.List;

public class PythonComplexityAnalyser extends ComplexityAnalyser {
    public PythonComplexityAnalyser() {
        this.complexityListener = new PythonComplexityMetricsListener();
    }

    @Override
    public void evaluateComplexity(List<String> files) throws IOException {
        for (String file : files) {
            PythonLexer lexer = new PythonLexer(CharStreams.fromFileName(file));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PythonParser parser = new PythonParser(tokens);
            ParseTree parseTree = parser.compound_stmt();
            ParseTreeWalker walker = new ParseTreeWalker();

            walker.walk(complexityListener, parseTree);

            updateMaximumAndAverageComplexityMap(file);
        }
    }
}
