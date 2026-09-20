class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            total += ('z' - s.charAt(i) + 1) * (i + 1);
        }
        return total;
    }
}