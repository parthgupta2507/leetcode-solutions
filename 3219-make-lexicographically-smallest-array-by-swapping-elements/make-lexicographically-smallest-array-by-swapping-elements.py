from typing import List

class Solution:
    def lexicographicallySmallestArray(self, nums: List[int], limit: int) -> List[int]:
        n = len(nums)
        # Pair each element with its original index and sort by value
        sorted_pairs = sorted(zip(nums, range(n)))
        
        result = [0] * n
        i = 0
        
        while i < n:
            j = i + 1
            # Find the boundary of the connected component where difference <= limit
            while j < n and sorted_pairs[j][0] - sorted_pairs[j - 1][0] <= limit:
                j += 1
            
            # Extract values and original indices for the current group
            group_values = [val for val, _ in sorted_pairs[i:j]]
            group_indices = sorted(idx for _, idx in sorted_pairs[i:j])
            
            # Place the sorted values into the sorted original indices
            for idx, val in zip(group_indices, group_values):
                result[idx] = val
                
            i = j  # Move to the next group
            
        return result