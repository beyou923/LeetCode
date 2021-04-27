class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];

        if(sum % 2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[sum];
        // 开始0/1背包
        for(int i = 0; i < nums.length; i++){
            // 每一个元素一定是不可重复放入，所以从大到小遍历
            for(int j = target; j >= nums[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        if(dp[target] == target) return true;
        return false;
    }
}