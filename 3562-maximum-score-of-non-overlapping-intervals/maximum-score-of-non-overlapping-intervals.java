import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] items = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            items[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        Arrays.sort(items, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.id, b.id);
        });

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0, right = i - 1;
            int last = -1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (items[mid].r < items[i].l) {
                    last = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            p[i] = last;
        }

        DP[][] dp = new DP[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new DP(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = items[i - 1];
            int prevIdx = p[i - 1] + 1;

            for (int k = 1; k <= 4; k++) {
                DP option1 = dp[i - 1][k];

                DP prevDP = dp[prevIdx][k - 1];
                long newWeight = prevDP.weight + curr.w;
                List<Integer> newIndices = new ArrayList<>(prevDP.indices);
                newIndices.add(curr.id);
                Collections.sort(newIndices);
                DP option2 = new DP(newWeight, newIndices);

                dp[i][k] = best(option1, option2);
            }
        }

        DP bestOverall = new DP(0, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            bestOverall = best(bestOverall, dp[n][k]);
        }

        int[] result = new int[bestOverall.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestOverall.indices.get(i);
        }
        return result;
    }

    private DP best(DP a, DP b) {
        if (a.weight > b.weight) return a;
        if (b.weight > a.weight) return b;
        return compareLexicographically(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compareLexicographically(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }

    private static class Interval {
        int l, r, w, id;
        Interval(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    private static class DP {
        long weight;
        List<Integer> indices;
        DP(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }
}