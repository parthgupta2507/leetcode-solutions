class Solution:
    def minimumDeletions(self, nums: list[int]) -> int:
        n = len(nums)
        if n <= 2:
            return n
        
        # 1. Find indices of min and max elements
        min_idx = nums.index(min(nums))
        max_idx = nums.index(max(nums))
        
        # 2. Identify lower index (i) and higher index (j)
        i = min(min_idx, max_idx)
        j = max(min_idx, max_idx)
        
        # 3. Calculate 3 options:
        # Option 1: Remove both from left
        from_left = j + 1
        
        # Option 2: Remove both from right
        from_right = n - i
        
        # Option 3: Remove left element from left, right element from right
        from_both = (i + 1) + (n - j)
        
        # 4. Return the minimum cost
        return min(from_left, from_right, from_both)