import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression, 0, expression.length() - 1);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parse(String expr, int start, int end) {
        Set<String> resultSet = new HashSet<>();
        List<Set<String>> currentProductGroup = new ArrayList<>();
        
        int i = start;
        while (i <= end) {
            char c = expr.charAt(i);
            
            if (c == ',') {
                resultSet.addAll(combineProductGroup(currentProductGroup));
                currentProductGroup.clear();
                i++;
            } else if (c == '{') {
                int level = 1;
                int j = i + 1;
                while (j <= end && level > 0) {
                    if (expr.charAt(j) == '{') level++;
                    else if (expr.charAt(j) == '}') level--;
                    j++;
                }
                Set<String> innerSet = parse(expr, i + 1, j - 2);
                currentProductGroup.add(innerSet);
                i = j;
            } else {
                int j = i;
                while (j <= end && Character.isLowerCase(expr.charAt(j))) {
                    j++;
                }
                Set<String> literalSet = new HashSet<>();
                literalSet.add(expr.substring(i, j));
                currentProductGroup.add(literalSet);
                i = j;
            }
        }
        
        if (!currentProductGroup.isEmpty()) {
            resultSet.addAll(combineProductGroup(currentProductGroup));
        }
        
        return resultSet;
    }

    private Set<String> combineProductGroup(List<Set<String>> group) {
        Set<String> res = new HashSet<>();
        res.add("");
        
        for (Set<String> set : group) {
            Set<String> nextRes = new HashSet<>();
            for (String s1 : res) {
                for (String s2 : set) {
                    nextRes.add(s1 + s2);
                }
            }
            res = nextRes;
        }
        
        return res;
    }
}