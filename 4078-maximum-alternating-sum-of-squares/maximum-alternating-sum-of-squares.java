import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] sq = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(sq);
        long sum = 0;
        int half = sq.length / 2;
        for (int i = 0; i < sq.length; i++) {
            if (i < half) {
                sum -= sq[i];
            } else {
                sum += sq[i];
            }
        }
        return sum;
    }
}