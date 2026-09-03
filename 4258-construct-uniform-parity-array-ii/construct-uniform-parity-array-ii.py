class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        min_odd = float('inf')
        
        # Step 1: Find the minimum odd number in the array
        for x in nums1:
            if x % 2 != 0:
                if x < min_odd:
                    min_odd = x
                    
        # Step 2: Check if there's any even number smaller than the minimum odd number
        for x in nums1:
            if x % 2 == 0:
                if min_odd != float('inf') and x < min_odd:
                    return False
                    
        return True