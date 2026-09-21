class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] result = new long[k];
        long[] count = new long[k];

        for (int i = 0; i < n; i++) {
            long[] nextCount = new long[k];
            int currentRemainder = nums[i] % k;

            nextCount[currentRemainder]++;

            for (int r = 0; r < k; r++) {
                if (count[r] > 0) {
                    int nextRemainder = (r * currentRemainder) % k;
                    nextCount[nextRemainder] += count[r];
                }
            }

            for (int r = 0; r < k; r++) {
                result[r] += nextCount[r];
            }

            count = nextCount;
        }

        return result;
    }
}