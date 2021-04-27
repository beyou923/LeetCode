## 题目

给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

**示例 1**：

>输入：nums = [1,5,11,5]
>输出：true
>解释：数组可以分割成 [1, 5, 5] 和 [11] 。

**示例 2**：

>输入：nums = [1,2,3,5]
>输出：false
>解释：数组不能分割成两个元素和相等的子集。

**提示**：

* 1 <= nums.length <= 200
* 1 <= nums[i] <= 100

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/partition-equal-subset-sum](https://leetcode-cn.com/problems/partition-equal-subset-sum?fileGuid=XCcPggyhXkWDYTXG)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

首先，本题要求集合里能否出现总和为 sum / 2 的子集。那么来一一对应一下本题，看看背包问题如果来解决。只有确定了如下四点，才能把01背包问题套到本题上来。

* 背包的体积为sum / 2
* 背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
* 背包如何正好装满，说明找到了总和为 sum / 2 的子集。
* 背包中每一个元素是不可重复放入。

以上分析完，我们就可以套用01背包，来解决这个问题了。

**动规五部曲分析如下**：

* 确定dp数组以及下标的含义

01背包中，dp[i] 表示：容量为j的背包，所背的物品价值可以最大为dp[j]。套到本题，dp[i]表示 背包总容量是i，最大可以凑成i的子集总和为dp[i]。

* 确定递推公式

01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);本题，相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]。所以递推公式：dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);

* dp数组如何初始化

首先dp[0]一定是0。如果如果题目给的价值都是正整数那么非0下标都初始化为0就可以了，如果题目给的价值有负数，那么非0下标就要初始化为负无穷。这样才能让dp数组在递归公式的过程中取的最大的价值，而不是被初始值覆盖了。本题题目中 只包含正整数的非空数组，所以非0下标的元素初始化为0就可以了。

* 确定遍历顺序

如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒叙遍历！

```java
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
```
时间复杂度：O(n)
空间复杂度：O(n)

