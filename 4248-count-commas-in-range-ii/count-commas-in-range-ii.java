class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        
        for (long start = 1000, commas = 1; start <= n; start *= 1000, commas++) {
            long end = start * 1000 - 1;
            long currentCount = Math.min(n, end) - start + 1;
            totalCommas += currentCount * commas;
        }

        return totalCommas;
    }
}