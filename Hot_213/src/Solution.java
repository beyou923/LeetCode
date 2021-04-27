public class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 2) return nums[0];
        if(len == 2) return Math.max(nums[0], nums[1]);

        // 情况一：不考虑首尾元素
        int status1 = rober(nums,1,len - 2);

        int status2 = rober(nums,0,len -2);

        // 情况三：考虑尾元素不考虑首元素
        int status3 = rober(nums,1,len - 1);

        // 返回3种情况中的最大值
        return Math.max(Math.max(status1, status2), status3);

    }
    // LeetCode 198 打家劫舍的逻辑
    public int rober(int[] nums, int left, int right){
        if(left == right) return nums[left];
        if(right - left == 1) return Math.max(nums[left], nums[right]);

        int[] dp = new int[nums.length];
        dp[left] = nums[left];
        dp[left + 1] = Math.max(nums[left], nums[left + 1]);
        for(int i = left + 2; i <= right; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[right];
    }
}
