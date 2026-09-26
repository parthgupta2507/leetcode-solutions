class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        double avg=0;
        for(int i=0;i<k;i++){
            avg += nums[i];
        }
        double maxavg=avg;
        for(int i=k;i<n;i++){
            avg=avg+nums[i]-nums[i-k];
            maxavg=Math.max(maxavg,avg);
        }
        return maxavg/k;
    }
}