class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int minAdditions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                if (openCount > 0) {
                    openCount--;
                } else {
                    minAdditions++;
                }
            }
        }

        return minAdditions + openCount;
    }
}