class Solution:
    def checkSubarraySum(self, nums: list[int], k: int) -> bool:
        first_occ = {0: -1}
        prefix_sum = 0

        for i, x in enumerate(nums):
            prefix_sum += x
            rem = prefix_sum % k

            if rem in first_occ:
                if i - first_occ[rem] >= 2:
                    return True
            else:
                first_occ[rem] = i

        return False