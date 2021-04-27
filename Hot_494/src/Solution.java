class Solution {
    int res;
    public int findTargetSumWays(int[] nums, int target) {
        res = 0;
        dfs(nums,target, 0);
        return res;
    }

    public void dfs(int[] nums, int target, int start){
        if(start >= nums.length){
            if(target == 0) res++;
            return;
        }
        // 递归对每个数加上正负号后与target计算，如果最终target变为0，res加1
        dfs(nums, target + nums[start], start + 1);
        dfs(nums, target - nums[start], start + 1);
    }
}

class Solution1 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums)
            sum += num;

        if(target > sum) return 0; // 此时没有方案
        if((target + sum) % 2 == 1) return 0; // 此时没有方案
        int bagSize = (target + sum) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j = bagSize; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[bagSize];
    }
}