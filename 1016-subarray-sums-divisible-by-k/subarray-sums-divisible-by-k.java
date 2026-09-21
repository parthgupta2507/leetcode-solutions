class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] count = new int[k];
        count[0] = 1;
        
        int prefixSum = 0;
        int result = 0;
        
        for (int num : nums) {
            prefixSum += num;
            int remainder = (prefixSum % k + k) % k;
            result += count[remainder];
            count[remainder]++;
        }
        
        return result;
    }
}