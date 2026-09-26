class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>seen=new HashMap<>();
        seen.put(0,1);
        int n=nums.length;

        int sum=0;
        int count=0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
            int needed=sum-k;
            if (seen.containsKey(needed)){
                count += seen.get(needed);
            }
            seen.put(sum, seen.getOrDefault(sum, 0) + 1);
        }
     return count;
    }
}