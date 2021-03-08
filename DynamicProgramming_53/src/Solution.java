/*
*   给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
*   示例:
*       输入: [-2,1,-3,4,-1,2,1,-5,4]
*       输出: 6
*       解释:连续子数组[4,-1,2,1] 的和最大，为 6。
*   链接：https://leetcode-cn.com/problems/maximum-subarray
* */
public class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int dp[] = new int[len];
        dp[0] = nums[0]; // 只有一个元素，那最大子数组的和就是这个元素
        int maxSum = dp[0]; //
        for (int i = 1 ; i < len; ++i) {
            if (dp[i-1] <= 0) // 以 i-1 个元素结尾的子数组的最大和如小于等于 0 只会越加越小 应该舍弃
                dp[i] = nums[i]; // 更新以第 i 个元素结尾的子数组的最大和
            else
                dp[i] = dp[i-1] + nums[i]; // 以 i-1 个元素结尾的子数组的最大和大于 0 只会越加越大 应该保留

            if (maxSum < dp[i]) { // 找 dp数组中的最大值
                maxSum = dp[i];
            }
        }
        return maxSum;
    }
}
