import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentScore = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(currentScore);
                currentScore = 0;
            } else {
                currentScore = stack.pop() + Math.max(2 * currentScore, 1);
            }
        }

        return currentScore;
    }
}