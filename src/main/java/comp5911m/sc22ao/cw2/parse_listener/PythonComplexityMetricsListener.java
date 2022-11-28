package comp5911m.sc22ao.cw2.parse_listener;

import comp5911m.sc22ao.cw2.parser.python.PythonParser;
import comp5911m.sc22ao.cw2.parser.python.PythonParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTree;

public class PythonComplexityMetricsListener extends PythonParserBaseListener implements ComplexityListener {
    @Override public void enterFuncdef(PythonParser.FuncdefContext ctx) {
        methodToComplexityMap.put(ctx.name().getText(), evaluateComplexityDFS(ctx) + 1);
    }
    @Override
    public Integer evaluateComplexityDFS(ParseTree ctx) {
        int count = 0;
        if (ctx.getChildCount() > 0) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                count += evaluateComplexityDFS(ctx.getChild(i));
            }
        } else {
            if (ctx.getText().equalsIgnoreCase("case")
                    || ctx.getText().equalsIgnoreCase("if")
                    || ctx.getText().equalsIgnoreCase("elif")
                    || ctx.getText().equalsIgnoreCase("and")
                    || ctx.getText().equalsIgnoreCase("or")
                    || ctx.getText().equalsIgnoreCase("for")
                    || ctx.getText().equalsIgnoreCase("while")
                    || ctx.getText().equalsIgnoreCase("else")
                    || ctx.getText().equalsIgnoreCase("repeat")) {
                return 1;
            } else {
                return 0;
            }
        }
        return count;
    }
}
