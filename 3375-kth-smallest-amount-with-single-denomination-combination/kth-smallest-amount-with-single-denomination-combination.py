import math

class Solution:
    def findKthSmallest(self, coins: list[int], k: int) -> int:
        n = len(coins)
        
        # Precompute the LCM and the sign (+1 or -1) for all 2^n - 1 non-empty subsets
        subsets = []
        for mask in range(1, 1 << n):
            subset_lcm = 1
            bits = 0
            for i in range(n):
                if (mask >> i) & 1:
                    subset_lcm = math.lcm(subset_lcm, coins[i])
                    bits += 1
            
            # Sign is +1 for odd size, -1 for even size
            sign = 1 if bits % 2 == 1 else -1
            subsets.append((subset_lcm, sign))
            
        def count_valid(val: int) -> int:
            total = 0
            for lcm_val, sign in subsets:
                total += sign * (val // lcm_val)
            return total

        # Binary search range
        left = 1
        right = min(coins) * k
        ans = right

        while left <= right:
            mid = (left + right) // 2
            if count_valid(mid) >= k:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans