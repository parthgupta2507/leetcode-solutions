class Solution:
    def stoneGameVIII(self, stones: list[int]) -> int:
        n = len(stones)
        
        # Compute prefix sums in-place
        for i in range(1, n):
            stones[i] += stones[i - 1]
            
        # Base case: at the last index, the player must take prefix[n - 1]
        dp = stones[-1]
        
        # Traverse backwards from n - 2 down to 1
        for i in range(n - 2, 0, -1):
            dp = max(dp, stones[i] - dp)
            
        return dp