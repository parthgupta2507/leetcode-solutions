class Solution:
    def findMin(self, nums: list[int]) -> int:
        left, right = 0, len(nums) - 1
        
        while left < right:
            mid = left + (right - left) // 2
            
            # If the middle element is greater than the rightmost element,
            # the minimum element must lie in the right half.
            if nums[mid] > nums[right]:
                left = mid + 1
            # Otherwise, the minimum is at mid or in the left half.
            else:
                right = mid
                
        return nums[left]

# Test cases to verify it works
if __name__ == "__main__":
    sol = Solution()
    print(sol.findMin([3, 4, 5, 1, 2]))        # Output: 1
    print(sol.findMin([4, 5, 6, 7, 0, 1, 2]))  # Output: 0
    print(sol.findMin([11, 13, 15, 17]))       # Output: 11
    