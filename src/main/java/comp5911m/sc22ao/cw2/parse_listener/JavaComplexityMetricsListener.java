package comp5911m.sc22ao.cw2.parse_listener;

import comp5911m.sc22ao.cw2.parser.java.JavaParser;
import comp5911m.sc22ao.cw2.parser.java.JavaParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class JavaComplexityMetricsListener extends JavaParserBaseListener implements ComplexityListener {
    private String previousNode;

    public JavaComplexityMetricsListener() {
        this.previousNode = "";
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        TerminalNode node = ctx.identifier().IDENTIFIER();
        String methodName = node.getText();
        methodToComplexityMap.put(methodName, evaluateComplexityDFS(ctx) + 1);
    }

    @Override
    public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        TerminalNode node = ctx.identifier().IDENTIFIER();
        String methodName = node.getText();
        methodToComplexityMap.put(methodName, evaluateComplexityDFS(ctx) + 1);
    }

    @Override
    public Integer evaluateComplexityDFS(ParseTree ctx) {
        int count = 0;
        if (ctx.getChildCount() > 0) {
            for (int i = 0; i < ctx.getChildCount(); i++) {
                count += evaluateComplexityDFS(ctx.getChild(i));
            }
        } else if (ctx.getText().equalsIgnoreCase("case")
                || ctx.getText().equalsIgnoreCase("if")
                || ctx.getText().equalsIgnoreCase("&&")
                || ctx.getText().equalsIgnoreCase("||")
                || ctx.getText().equalsIgnoreCase("for")
                || ctx.getText().equalsIgnoreCase("while")
                || ctx.getText().equalsIgnoreCase("else")
                || ctx.getText().equalsIgnoreCase("repeat")) {
            if (ctx.getText().equalsIgnoreCase("if") && previousNode.equals("else")) {
                previousNode = ctx.getText();
                return 0;
            } else {
                previousNode = ctx.getText();
                return 1;
            }
        } else {
            return 0;
        }
        return count;
    }
}
