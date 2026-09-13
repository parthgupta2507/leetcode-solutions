class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        for (int rowOffset = -n + 1; rowOffset < n; rowOffset++) {
            for (int colOffset = -n + 1; colOffset < n; colOffset++) {
                maxOverlap = Math.max(maxOverlap, countOverlap(img1, img2, rowOffset, colOffset));
            }
        }

        return maxOverlap;
    }

    private int countOverlap(int[][] img1, int[][] img2, int rowOffset, int colOffset) {
        int n = img1.length;
        int count = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int targetR = r + rowOffset;
                int targetC = c + colOffset;

                if (targetR >= 0 && targetR < n && targetC >= 0 && targetC < n) {
                    if (img1[r][c] == 1 && img2[targetR][targetC] == 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}