## 题目

你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

**示例 1**：

>输入：nums = [2,3,2]
>输出：3
>解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

**示例 2**：

>输入：nums = [1,2,3,1]
>输出：4
>解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
>偷窃到的最高金额 = 1 + 3 = 4 。

**示例 3**：

>输入：nums = [0]
>输出：0



**提示**：

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 1000

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/house-robber-ii](https://leetcode-cn.com/problems/house-robber-ii?fileGuid=VdxYTrG6G6dPdvPc)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

这道题目和[LeetCode 198](https://leetcode-cn.com/problems/house-robber/?fileGuid=VdxYTrG6G6dPdvPc)打家劫舍是差不多的，唯一区别就是成环了。对于一个数组，成环的话主要有如下三种情况：

* 不考虑首尾元素
* 考虑首元素不考虑尾元素
* 考虑尾元素不考虑首元素

分别对3种情况调用LeetCode 198的方法得到3个值，最后返回3个值中的最大值

```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len < 2) return nums[0];
        if(len == 2) return Math.max(nums[0], nums[1]);
        // 情况一：不考虑首尾元素
        int status1 = rober(nums,1,len - 2);
       
        int status2 = rober(nums,0,len -2);
    
        // 情况三：考虑尾元素不考虑首元素
        int status3 = rober(nums,1,len - 1);
        // 返回3种情况中的最大值
        return Math.max(Math.max(status1, status2), status3);
    }
    // LeetCode 198 打家劫舍的逻辑
    public int rober(int[] nums, int left, int right){
        if(left == right) return nums[left];
        if(right - left == 1) return Math.max(nums[left], nums[right]);
        int[] dp = new int[nums.length];
        dp[left] = nums[left];
        dp[left + 1] = Math.max(nums[left], nums[left + 1]);
        for(int i = left + 2; i <= right; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[right];
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

