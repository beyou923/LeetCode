## 题目

给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

**示例**：

>输入：nums: [1, 1, 1, 1, 1], S: 3
>输出：5
>解释：
>>-1+1+1+1+1 = 3
>+1-1+1+1+1 = 3
>+1+1-1+1+1 = 3
>+1+1+1-1+1 = 3
>+1+1+1+1-1 = 3
>>一共有5种方法让最终目标和为3。

**提示**：

* 数组非空，且长度不会超过 20 。
* 初始的数组的和不会超过 1000 。
* 保证返回的最终结果能被 32 位整数存下。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/target-sum](https://leetcode-cn.com/problems/target-sum?fileGuid=XtYdQKQWPhcpCxpx)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：DFS

从第一个数字，调用递归函数，在递归函数中，分别对目标值进行加上当前数字调用递归，和减去当前数字调用递归，这样会涵盖所有情况，并且当所有数字遍历完成后，若目标值为0了，则结果 res 自增1

```java
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
```
时间复杂度：O(2^n)
空间复杂度：O(n)

### 方法二：动态规划

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        
        if(target > sum) return 0; // 此时没有方案
        if((target + sum) % 2 == 1) return 0; // 此时没有方案
        int bagSize = (target + sum) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++){
            for(int j = bagSize; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[bagSize];
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(m)

其中，n为正数个数，m为背包容量

