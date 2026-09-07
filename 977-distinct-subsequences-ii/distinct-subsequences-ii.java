import java.util.Arrays;

class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] endsIn = new long[26];
        long currentTotal = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            long prevEndsIn = endsIn[index];
            endsIn[index] = (currentTotal + 1) % MOD;
            currentTotal = (currentTotal + endsIn[index] - prevEndsIn + MOD) % MOD;
        }

        return (int) currentTotal;
    }
}