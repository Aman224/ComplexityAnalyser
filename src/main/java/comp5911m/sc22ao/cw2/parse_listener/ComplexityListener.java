package comp5911m.sc22ao.cw2.parse_listener;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.HashMap;
import java.util.Map;

public interface ComplexityListener extends ParseTreeListener {
    Map<String, Integer> methodToComplexityMap = new HashMap<>();
    Integer evaluateComplexityDFS(ParseTree ctx);

    default Map<String, Integer> getMethodToComplexityMap() {
        return methodToComplexityMap;
    }

    default void clearMethodToComplexityMap() {
        methodToComplexityMap.clear();
    };
}
