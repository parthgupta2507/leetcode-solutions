import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder keyBuffer = new StringBuilder();
        boolean inBracket = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                inBracket = false;
                String key = keyBuffer.toString();
                result.append(map.getOrDefault(key, "?"));
                keyBuffer.setLength(0); // Clear the buffer
            } else if (inBracket) {
                keyBuffer.append(c);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}